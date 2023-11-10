package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class MemberController {


    // ************* join / login **************

    // 생성자 주입
    private final MemberService memberService;
    private final EntryService entryService;
    private final FeedbackCommentService feedbackCommentService;
    private final AccountAnalyzeService accountAnalyzeService;
    private final ChatGptService chatGptService;
    private final MissionService missionService;
    private final FeedbackService feedbackService;
    private final ProfileService profileService;

    // 회원가입 로직
    @PostMapping("/member/join")   // 나중에 RequestMapping으로 수정
    public MemberDTO join(@RequestBody MemberDTO memberDTO){ // /join에서 받은 회원가입 정보를 /member/join에서 받아오기
        memberService.save(memberDTO);  // 받아온 값으로 회원가입하기, 이미 있는 회원 고려 안 함 >> 우리가 값 넣을 때 없는 값으로만 넣기.
        entryService.save(); // 회원가입 하면 자동으로 entry_table에 값 들어가도록
        feedbackCommentService.save(); // 회원가입 하면 자동으로 feedback_comment_table에 값 들어가도록
        return memberDTO;
    }

    // 로그인 로직
    @PostMapping("/member/login")
    public String login(@RequestBody MemberDTO memberDTO){ // /login에서 받은 로그인 정보를 /member/login에서 받아오기
        MemberDTO loginResult = memberService.login(memberDTO); // 해당 회원이 member_table에 있는지 확인
        if (loginResult != null) { // login 성공
            SurveyDTO surveyDTO = surveyService.findBySurveyId(memberDTO.getMemberId()); //survey_table에 해당 회원의 정보가 있는지 확인
            if (surveyDTO != null){ // 이미 설문조사를 한 회원
                List<AccountAnalyzeDTO> dtos = accountAnalyzeService.findByMemberIdAndOkToUse(memberDTO.getMemberId(),false);
                if(dtos.isEmpty()){ // 이번 주차 피드백, 미션이 만들어진 경우
                    return "{\"message\" : \"success\"}";
                }
                else { // 이번 주차 피드백, 미션이 만들어지지 않은 경우

                    // 전제조건 : 이번주차 결제내역 링크를 통해 가져온 상태여야 함.

                    // 이번 주 미션, 피드백 문장을 만들기 위한 [ 해당 회원 id / 미션, 피드백 시작 날짜 정보 ]
                    String selectedMemberId = surveyDTO.getSurveyId();
                    String startDate = accountAnalyzeService.findThisWeek(selectedMemberId);

                    // 1. 저번 주 미션 성공여부 확인
                    int missionId = missionService.findbyNow("true");
                    missionService.isMissionSuccess(missionId);

                    // 2. 미션 성공한 경우 profile의 successCnt, level, position, successMission(최근에 성공한 미션 3개) update
                    // 실패한 경우는 update X
                    profileService.updateSuccess(missionId); // 데모를 위해 무조건 success만 했다고 가정

                    // 3. 저번주(아직 DB엔 이번주로 되어있음) 미션, 피드백을 지난 주차로 바꾸고 이번 주 미션 피드백이 아직 없는 걸로 설정
                    // 지난 주차로 바꾸는 것 : 피드백 okToSend : false -> true, 미션 now : true -> false
                    String lastStartDate = accountAnalyzeService.findLastWeek(selectedMemberId); // lastStartDate = "1108"
                    feedbackService.changeWeek(selectedMemberId, lastStartDate);
                    missionService.changeWeek(selectedMemberId, lastStartDate);

                    // 4. 미션 로직을 통해 이번 주차 미션 항목 선정
                    missionService.mission(selectedMemberId);

                    // 5. 선정된 항목으로 미션 문장 만들고 저장
                    ChatGptResponse chatGptResponseForMission = null;
                    chatGptResponseForMission = chatGptService.askQuestionM(selectedMemberId, startDate);
                    String missionContent = chatGptResponseForMission.getChoices().get(0).getMessage().getContent();
                    System.out.println(missionContent);
                    missionService.saveMissionSen(selectedMemberId, startDate, missionContent);

                    // 6. 선정된 항목과 분석 테이블의 totalAmount를 토대로 피드백 문장 만들고 저장
                    ChatGptResponse chatGptResponseForFeedback = null;
                    chatGptResponseForFeedback = chatGptService.askQuestion(selectedMemberId, startDate);
                    String feedbackContent = chatGptResponseForFeedback.getChoices().get(0).getMessage().getContent();
                    feedbackService.save(selectedMemberId, feedbackContent, startDate);

                    // 7. 이번 주차 미션과 피드백 문장 생성 후 분석 테이블에 이번 주차 항목들의 OkToUse True로 변경
                    accountAnalyzeService.changeOkToUseWithTrue(selectedMemberId);

                    // 이번 주차 미션, 피드백 생성 및 DB반영 완료


                    return "{\"message\" : \"success\"}";
                }
            }
            else { // 아직 설문조사를 하지 않은 회원 (회원가입 후 첫 로그인일 때 == survey table에 해당 id를 가진 회원의 정보가 없을 때)
                return "{\"message\" : \"successFirst\"}";
            }
        } else {  // login 실패
            return "{\"message\" : \"fail\"}";
        }
    }

    // 회원 정보 보여주기
    @RequestMapping("/api/v1/member")
    @ResponseBody
    public MemberDTO postMember() { //member정보 보내기
        MemberDTO loginResult = memberService.postMember();
        return loginResult;
    }

    // ************* survey **************


    private final SurveyService surveyService;

    // 설문조사 작성하고 db에 저장하기
    @PostMapping(value="/member/survey")
    public SurveyDTO setSurveyDTO(@RequestBody SurveyDTO surveyDTO){

        System.out.println(surveyDTO);
        surveyService.save(surveyDTO);

        // 설문조사 끝나자 마자 최근 일주일 간의 결제내역 분석결과를 기반으로 미션 생성 (payment 객체 불러와서 AA table에 분석 결과가 저장되어있는 상태에서)

        // 미션, 피드백 문장을 만들기 위한 [ 해당 회원 id / 미션, 피드백 시작 날짜 정보 ]
        String selectedMemberId = surveyDTO.getSurveyId();
        String startDate = accountAnalyzeService.findThisWeek(selectedMemberId);

        // 1. 미션 로직을 통해 미션 항목 선정
        missionService.mission(selectedMemberId);

        // 2. 선정된 항목으로 미션 문장 만들고 저장
        ChatGptResponse chatGptResponseForMission = null;
        chatGptResponseForMission = chatGptService.askQuestionM(selectedMemberId, startDate);
        String missionContent = chatGptResponseForMission.getChoices().get(0).getMessage().getContent();
        System.out.println(missionContent);
        missionService.saveMissionSen(selectedMemberId, startDate, missionContent);

        // 3. 선정된 항목과 분석 테이블의 totalAmount를 토대로 피드백 문장 만들고 저장
        ChatGptResponse chatGptResponseForFeedback = null;
        chatGptResponseForFeedback = chatGptService.askQuestion(selectedMemberId, startDate);
        String feedbackContent = chatGptResponseForFeedback.getChoices().get(0).getMessage().getContent();
        feedbackService.save(selectedMemberId, feedbackContent, startDate);

        // 4. 이번 주차 미션과 피드백 문장 생성 후 분석 테이블에 이번 주차 항목들의 OkToUse True로 변경
        accountAnalyzeService.changeOkToUseWithTrue(selectedMemberId);

        // 5. profile goalEntry 업데이트
        profileService.updateGoalEntry(selectedMemberId);

        return surveyDTO;
    }

    // 설문조사 보여주기(로그인했을 때 id 가져와서)

    @RequestMapping("/surveyDTO")
    @ResponseBody
    public SurveyDTO sendSurveyDTO(@RequestBody String memberId){
        SurveyDTO surveyDTO = surveyService.findBySurveyId(memberId);
        return surveyDTO;
    }



    // ************* survey_limit **************

    private final SurveyLimitService surveyLimitService;

    @RequestMapping("/surveyLimitDTO")
    @ResponseBody
    public SurveyLimitDTO sendSurveyLimit(@RequestBody SurveyDTO surveyDTO){
        int entry1totalAmount = accountAnalyzeService.findbySurveyIdAndEntry(surveyDTO.getSurveyId(), surveyDTO.getGoalEntry1());
        int entry2totalAmount = accountAnalyzeService.findbySurveyIdAndEntry(surveyDTO.getSurveyId(), surveyDTO.getGoalEntry2());
        int entry3totalAmount = accountAnalyzeService.findbySurveyIdAndEntry(surveyDTO.getSurveyId(), surveyDTO.getGoalEntry3());

        SurveyLimitDTO surveyLimitDTO = surveyLimitService.save(surveyDTO.getSurveyId(), entry1totalAmount, entry2totalAmount, entry3totalAmount);
        return surveyLimitDTO;
    }

}
//MemberController.class