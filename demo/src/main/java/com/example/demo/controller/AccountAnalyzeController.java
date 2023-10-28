package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountAnalyzeController {

    private final PaymentService paymentService;
    private final AccountAnalyzeService accountAnalyzeService;
    private final SurveyService surveyService;
    String memberId = "qq";

    @GetMapping("/member/payment")
    public void paymentForm() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/transactions?startDate=2022-01-01T00:00:00&endDate=2023-10-20T23:59:59"))
                .header("Authorization", "Basic dGVzdF9za19leDZCSkdRT1ZEOUVhR3hYNVpSclc0dzJ6TmJnOg==")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        String paymentstr = response.body();
        paymentstr = paymentstr.replace("mId", "mid");

        ObjectMapper mapper = new ObjectMapper();
        try {
            // 스트링에서 DTO로 매핑하기
            //PaymentDTO paymentDTO = mapper.readValue(paymentstr, PaymentDTO.class);
            //System.out.println(paymentDTO);

            List<PaymentDTO> dtos = Arrays.asList(mapper.readValue(paymentstr, PaymentDTO[].class));
            System.out.println(dtos.size() + "개의 dto : " +dtos);

            for(PaymentDTO pay : dtos){
                String data[] = pay.getOrderId().split("-");
                if(pay.getStatus().equals("DONE") && data[0].equals("pay")){  //CANCELED, DONE, WAITING_FOR_DEPOSIT
                    pay.setEntry(data[1]);
                    pay.setOrderTime(data[3]);
                    pay.setMemberId(memberId);
                    //System.out.println(pay);
                    paymentService.save(pay);
                }
            }
            accountAnalyzeService.saveAmount(memberId);


        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @GetMapping("/makeMission")
    public void mission() {

        String missionEntry = "";
        int missionMoney = 0;
        List<AccountAnalyzeDTO> dtos = accountAnalyzeService.findByMemberId(memberId);
        System.out.println(dtos);
        List<String> missionEntries = new ArrayList<>();
        for (AccountAnalyzeDTO useEntry : dtos) {
            missionEntries.add(useEntry.getEntry());
        }
        System.out.println(missionEntries);

        //survey_table의 fixed_entry는 미션 항목에서 제외
        SurveyDTO surveyDTO = surveyService.findBySurveyId(memberId);
        List<String> fixedEntry = Arrays.asList(surveyDTO.getFixedEntry().split(","));
        for (AccountAnalyzeDTO useEntry : dtos) {
            for (String fEntry : fixedEntry) {
                if (useEntry.getEntry().equals(fEntry)) {
                    missionEntries.remove(fEntry);
                }
            }
        }
        System.out.println(missionEntries);

        //survey_table의 목표금액과 차가 큰 항목을 우선해서 미션 줌
        int diff_max = 0;

        for (AccountAnalyzeDTO useEntry : dtos) {
            if (useEntry.getEntry().equals(surveyDTO.getGoalEntry1())) {
                if (useEntry.getTotalAmount() - surveyDTO.getGoalMoney1() > diff_max){
                    diff_max = useEntry.getTotalAmount() - surveyDTO.getGoalMoney1();
                    missionEntry = surveyDTO.getGoalEntry1();
                    missionMoney = surveyDTO.getGoalMoney1();
                }
            }
            if (useEntry.getEntry().equals(surveyDTO.getGoalEntry2())) {
                if (useEntry.getTotalAmount() - surveyDTO.getGoalMoney2() > diff_max){
                    diff_max = useEntry.getTotalAmount() - surveyDTO.getGoalMoney2();
                    missionEntry = surveyDTO.getGoalEntry2();
                    missionMoney = surveyDTO.getGoalMoney2();
                }
            }
            if (useEntry.getEntry().equals(surveyDTO.getGoalEntry3())) {
                if (useEntry.getTotalAmount() - surveyDTO.getGoalMoney3() > diff_max){
                    diff_max = useEntry.getTotalAmount() - surveyDTO.getGoalMoney3();
                    missionEntry = surveyDTO.getGoalEntry3();
                    missionMoney = surveyDTO.getGoalMoney3();
                }
            }
        }
        if(missionMoney>0) {
            missionMoney += ((diff_max / 2) / 1000) * 1000;
        }else{                      //목표금액보다 많이 쓴 항목이 없다면?
            System.out.println("목표금액보다 많이 쓴 항목이 없다.");
        }

        System.out.println(missionEntry);
        System.out.println(missionMoney);
    }


}
