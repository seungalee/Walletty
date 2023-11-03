package com.example.demo.controller;

import com.example.demo.dto.AccountAnalyzeDTO;
import com.example.demo.dto.MissionDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.MissionEntity;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.service.AccountAnalyzeService;
import com.example.demo.service.MissionService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final AccountAnalyzeService accountAnalyzeService;
    private final AccountAnalyzeRepository accountAnalyzeRepository;

    private final SurveyService surveyService;
    private final MissionService missionService;


    //String memberId = "aa";

    @GetMapping("/makeMission")
    public void mission() {

        String missionEntry = "";
        int missionMoney = 0;

        //List<AccountAnalyzeDTO> dtos = accountAnalyzeService.findByMemberIdAndOkToUse(memberId,false);
        List<AccountAnalyzeDTO> dtos = accountAnalyzeService.findByOkToUse(false);
        System.out.println(dtos);

        List<String> missionEntries = new ArrayList<>();

        int missionStart = 0;
        String memberId = "";
        for (AccountAnalyzeDTO useEntry : dtos) {
            missionEntries.add(useEntry.getEntry());
            missionStart = Integer.parseInt(useEntry.getOrderWeek()) + 1;
            memberId = useEntry.getMemberId();
        }
        System.out.println(missionEntries);

        // 가장 먼저 지난 미션 항목은 미션을 주지 않음
        String lastMissionDate = Integer.toString(missionStart - 7);
        MissionDTO lastmission = missionService.findByStartDate(lastMissionDate);

        if (lastmission == null) {
            System.out.println("ji");
        } else {


            SurveyDTO surveyDTO = surveyService.findBySurveyId(memberId);
            //survey_table의 목표금액과 차가 큰 항목을 우선해서 미션 줌
            int diff_max = 0;

            for (AccountAnalyzeDTO useEntry : dtos) {

                // 지난 항목이면 무시
                if (!(useEntry.getEntry().equals(lastmission.getMissionEntry()))) {

                    if (useEntry.getEntry().equals(surveyDTO.getGoalEntry1())) {
                        if (useEntry.getTotalAmount() - surveyDTO.getGoalMoney1() > diff_max) {
                            diff_max = useEntry.getTotalAmount() - surveyDTO.getGoalMoney1();
                            missionEntry = surveyDTO.getGoalEntry1();
                            missionMoney = surveyDTO.getGoalMoney1();
                        }
                    }
                    if (useEntry.getEntry().equals(surveyDTO.getGoalEntry2())) {
                        if (useEntry.getTotalAmount() - surveyDTO.getGoalMoney2() > diff_max) {
                            diff_max = useEntry.getTotalAmount() - surveyDTO.getGoalMoney2();
                            missionEntry = surveyDTO.getGoalEntry2();
                            missionMoney = surveyDTO.getGoalMoney2();
                        }
                    }
                    if (useEntry.getEntry().equals(surveyDTO.getGoalEntry3())) {
                        if (useEntry.getTotalAmount() - surveyDTO.getGoalMoney3() > diff_max) {
                            diff_max = useEntry.getTotalAmount() - surveyDTO.getGoalMoney3();
                            missionEntry = surveyDTO.getGoalEntry3();
                            missionMoney = surveyDTO.getGoalMoney3();
                        }
                    }
                }
            }
            if (missionMoney > 0) {
                missionMoney += ((diff_max / 2) / 1000) * 1000;
            } else {//목표금액보다 많이 쓴 항목이 없다면? 지난 항목과 고정 항목을 제외하고 가장 많이 사용한 항목에서 10% 줄이라고 함.

                //지난 항목과 survey_table의 fixed_entry는 미션 항목에서 제외
                List<String> fixedEntry = Arrays.asList(surveyDTO.getFixedEntry().split(","));
                for (AccountAnalyzeDTO useEntry : dtos) {
                    for (String fEntry : fixedEntry) {
                        if (useEntry.getEntry().equals(fEntry)) {
                            missionEntries.remove(fEntry);
                        }
                        if (useEntry.getEntry().equals(lastmission.getMissionEntry())) {
                            missionEntries.remove(fEntry);
                        }
                    }
                }
                System.out.println(missionEntries);
                System.out.println("목표금액보다 많이 쓴 항목이 없다.");

                int maxUse = 0;
                for (AccountAnalyzeDTO useEntry : dtos) {
                    //survey_table의 fixed_entry가 아니라면
                    if (missionEntries.contains(useEntry.getEntry())) {
                        if (useEntry.getTotalAmount() > maxUse) {
                            missionEntry = useEntry.getEntry();
                            missionMoney = useEntry.getTotalAmount();
                        }
                    }
                }
                missionMoney = (int) (missionMoney * 0.9);
                missionMoney = ((missionMoney / 1000) * 1000);

            }

            System.out.println(missionEntry);
            System.out.println(missionMoney);

            int missionEnd = missionStart + 7;
            String startDate = Integer.toString(missionStart);
            String endDate = Integer.toString(missionEnd);

            //MissionDTO에 저장(missionId, memberId, missionEntry, missionMoney, now, startDate까지)
            MissionDTO missionDTO = new MissionDTO();
            missionDTO.setMemberId(memberId);
            missionDTO.setMissionEntry(missionEntry);
            missionDTO.setMissionMoney(missionMoney);
            missionDTO.setStartDate(startDate);
            missionDTO.setEndDate(endDate);
            missionDTO.setNow("True");
            missionService.save(missionDTO);

            missionService.changeOkToUseWithMissionEntry(missionEntry, memberId, Integer.toString(missionStart - 1));
        }
    }

}
