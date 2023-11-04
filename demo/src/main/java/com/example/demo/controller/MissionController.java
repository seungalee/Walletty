package com.example.demo.controller;

import com.example.demo.dto.AccountAnalyzeDTO;
import com.example.demo.dto.MissionDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.service.AccountAnalyzeService;
import com.example.demo.service.MissionService;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final AccountAnalyzeService accountAnalyzeService;
    private final SurveyService surveyService;
    private final MissionService missionService;

    // 이번주 사용 금액과 저번주 미션 금액을 비교해서 미션 성공 실패를 알림.
    @RequestMapping("/isMissionSuccess/{missionId}")
    public void isSuccess(@PathVariable("missionId") int missionId){

        missionService.isMissionSuccess(missionId);
    }

    public void mission(String memberId) {
        String missionEntry = "";
        int missionMoney = 0;

        // 1. 분석테이블을 보고 사용한 항목은 모두 missionCandi에
        List<AccountAnalyzeDTO> dtos = accountAnalyzeService.findByMemberIdAndOkToUse(memberId,false);
        System.out.println(dtos);

        List<String> missionCandi = new ArrayList<>();

        int missionStart = 0;
        for (AccountAnalyzeDTO useEntry : dtos) {
            missionCandi.add(useEntry.getEntry());
            missionStart = Integer.parseInt(useEntry.getOrderWeek()) + 1;
        }
        System.out.println(missionCandi);

        //2. 지난 미션이 있으면 항목 삭제
        String lastMissionDate = Integer.toString(missionStart - 7);
        MissionDTO lastmission = missionService.findByMemberIdAndStartDate(memberId,lastMissionDate);

        if(lastmission != null){


            missionCandi.remove(lastmission.getMissionEntry());
            System.out.println("제거완료");
            System.out.println(lastmission.getMissionEntry());
        }

        System.out.println(missionCandi);

        //3. fixed_entry 삭제
        SurveyDTO surveyDTO = surveyService.findBySurveyId(memberId);

        String[] fixedEntry = surveyDTO.getFixedEntry().split(",");
        for (AccountAnalyzeDTO useEntry : dtos) {
            for (String fEntry : fixedEntry) {
                if (useEntry.getEntry().equals(fEntry)) {
                    missionCandi.remove(fEntry);
                }
            }
        }
        System.out.println(missionCandi);

        // 4. 목표 항목과 소비의 차가 가장 큰 항목을 고름
        // 4-1. 분석 테이블에 지난주가 없으면 그냥 4를 미션으로 줌
        int diff_max = 0;
        for (AccountAnalyzeDTO useEntry : dtos) {
            if(missionCandi.contains(useEntry.getEntry())) {
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
                System.out.println(missionEntry);
            }
        }
        // 4-1. 분석 테이블에 지난 주가 없으면 그냥 4 줌
        if (diff_max > 0 && lastmission == null) {
            missionMoney += ((diff_max / 2) / 1000) * 1000;

            System.out.println(missionEntry);
            System.out.println(missionMoney);

            int missionEnd = missionStart + 6;
            String startDate = Integer.toString(missionStart);
            String endDate = Integer.toString(missionEnd);

            //MissionDTO에 저장(missionId, memberId, missionEntry, missionMoney, now, startDate까지)
            MissionDTO missionDTO = makeMissionDTO(memberId, missionEntry, missionMoney, startDate, endDate);

            missionService.save(missionDTO);

        }//4-2. 분석 테이블에 지난 주가 있으면 지난 주보다 훨씬 많이 쓴 항목을 계산하고 4번과 비교한 후 4번보다 3배 이상 차이가 크다면 미션으로 줌
        else if(diff_max > 0 && lastmission != null){
            MissionDTO missionDTOWithLastWeek = compareWithLastWeek(memberId, missionCandi, diff_max, missionStart);
            if(missionDTOWithLastWeek != null){

                missionService.save(missionDTOWithLastWeek);
            }
            else{
                missionMoney += ((diff_max / 2) / 1000) * 1000;

                System.out.println(missionEntry);
                System.out.println(missionMoney);

                int missionEnd = missionStart + 6;
                String startDate = Integer.toString(missionStart);
                String endDate = Integer.toString(missionEnd);


                MissionDTO missionDTO = makeMissionDTO(memberId, missionEntry, missionMoney, startDate, endDate);

                missionService.save(missionDTO);
            }
        }
        //이게 5인 상황
        //5. 목표 항목이 모두 다 목표를 지켰으면
        else{
            System.out.println("목표금액보다 많이 쓴 항목이 없다.");

            int maxUse = 0;
            for (AccountAnalyzeDTO useEntry : dtos) {

                if (missionCandi.contains(useEntry.getEntry())) {
                    if (useEntry.getTotalAmount() > maxUse) {
                        missionEntry = useEntry.getEntry();
                        missionMoney = useEntry.getTotalAmount();
                    }
                }
            }
            missionMoney = (int) (missionMoney * 0.9);
            missionMoney = ((missionMoney / 1000) * 1000);

            System.out.println(missionEntry);
            System.out.println(missionMoney);

            int missionEnd = missionStart + 6;
            String startDate = Integer.toString(missionStart);
            String endDate = Integer.toString(missionEnd);


            MissionDTO missionDTO = makeMissionDTO(memberId, missionEntry, missionMoney, startDate, endDate);

            missionService.save(missionDTO);
        }


    }

    public MissionDTO compareWithLastWeek(String memberId, List<String> missionCandi, int mission_diff, int missionStart){
        List<AccountAnalyzeDTO> nowDtos = accountAnalyzeService.findByMemberIdAndOkToUse(memberId,false);

        String orderWeek = "";
        for (AccountAnalyzeDTO useEntry : nowDtos) {
            orderWeek = useEntry.getOrderWeek();
        }
        orderWeek = Integer.toString(Integer.parseInt(orderWeek) - 7);

        List<AccountAnalyzeDTO> lastDtos = accountAnalyzeService.findByMemberIdAndOrderWeek(memberId, orderWeek);
        System.out.println(lastDtos);

        int last_diff = 0;
        String missionEntry = "";
        int missionMoney = 0;
        for (AccountAnalyzeDTO now : nowDtos) {
            for (AccountAnalyzeDTO last : lastDtos) {

                if(now.getEntry().equals(last.getEntry()) && missionCandi.contains(now.getEntry())){

                    int diff = now.getTotalAmount() - last.getTotalAmount();
                    if(diff > last_diff){
                        last_diff = diff;
                        missionEntry = now.getEntry();
                        missionMoney = last.getTotalAmount();
                    }
                }
            }
        }
        if(last_diff > mission_diff * 3){
            System.out.println("지난 주와의 사용 금액의 차가 이번 주와 미션 금액의 차의 3배 이상이므로, 지난 주에 많이 사용한 항목을 미션으로 줍니다.");

            missionMoney += ((last_diff / 2) / 1000) * 1000;

            System.out.println(missionEntry);
            System.out.println(missionMoney);

            int missionEnd = missionStart + 6;
            String startDate = Integer.toString(missionStart);
            String endDate = Integer.toString(missionEnd);

            MissionDTO missionDTO = makeMissionDTO(memberId, missionEntry, missionMoney, startDate, endDate);
            //missionService.save(missionDTO);

            return missionDTO;
        }
        else{
            return null;
        }
    }
    public MissionDTO makeMissionDTO(String memberId, String missionEntry, int missionMoney, String startDate, String endDate){
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setMemberId(memberId);
        missionDTO.setMissionEntry(missionEntry);
        missionDTO.setMissionMoney(missionMoney);
        missionDTO.setStartDate(startDate);
        missionDTO.setEndDate(endDate);
        missionDTO.setNow("True");

        return missionDTO;

    }



}
