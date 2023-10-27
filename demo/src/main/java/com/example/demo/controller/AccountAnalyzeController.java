package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountAnalyzeController {
    
    private final PaymentService paymentService;
    private final AccountAnalyzeService accountAnalyzeService;

    String memberId = "aa";

    @GetMapping("/member/payment")
    public void paymentForm() throws IOException, InterruptedException {

        /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/orders/qqqqq123I893LDucg"))
                .header("Authorization", "Basic dGVzdF9za19leDZCSkdRT1ZEOUVhR3hYNVpSclc0dzJ6TmJnOg==")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        */

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

}
