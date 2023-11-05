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
    private final MissionController missionController;
    private final FeedbackService feedbackService;
    private final ProfileService profileService;

    // 회원가입 페이지 출력 요청
//    @GetMapping("/member/save")
//    public String saveForm() {
//        return "save";
//    }

//    @PostMapping("/member/save")    // name값을 requestparam에 담아온다
//    public String save(@ModelAttribute MemberDTO memberDTO) {
//        System.out.println("MemberController.save");
//        System.out.println("memberDTO = " + memberDTO);
//        memberService.save(memberDTO);
//
//        return "login";
//    }

//    @GetMapping("/member/join")  // /join에서 받은 회원가입 정보를 /member/join에서 받아오기
//    public ResponseEntity join(@RequestBody MemberDTO memberDTO){
//        memberService.save(memberDTO);
//        //log.debug("joinInfo = {}", memberDTO.toString());
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }

    // 회원가입 로직
    @PostMapping("/member/join")   // 나중에 RequestMapping으로 수정
    public MemberDTO join(@RequestBody MemberDTO memberDTO){ // /join에서 받은 회원가입 정보를 /member/join에서 받아오기
        memberService.save(memberDTO);  // 받아온 값으로 회원가입하기, 이미 있는 회원 고려 안 함 >> 우리가 값 넣을 때 없는 값으로만 넣기.
        entryService.save(); // 회원가입 하면 자동으로 entry_table에 값 들어가도록
        feedbackCommentService.save(); // 회원가입 하면 자동으로 feedback_comment_table에 값 들어가도록
        return memberDTO;
    }

//    @GetMapping("/member/login")
//    public String loginForm() {
//        return "login";
//    }


//    @PostMapping("/member/login") // session : 로그인 유지
//    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
//        MemberDTO loginResult = memberService.login(memberDTO);
//        if (loginResult != null) {
//            // login 성공
//            session.setAttribute("loginId", loginResult.getMemberId());
//            return "main";
//        } else {
//            // login 실패
//            return "login";
//        }
//    }

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

                    // 미션, 피드백 문장을 만들기 위한 [ 해당 회원 id / 미션, 피드백 시작 날짜 정보 ]
                    String selectedMemberId = surveyDTO.getSurveyId();
                    String startDate = accountAnalyzeService.findThisWeek(selectedMemberId);

                    // 1. 미션 로직을 통해 미션 항목 선정
                    missionController.mission(selectedMemberId);

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

    /*
    @GetMapping("/member/survey")
    public String surveyForm() {
        return "survey";
    }

    @PostMapping("/member/survey")
    public String survey(@ModelAttribute SurveyDTO surveyDTO) {
        System.out.println("MemberController.survey");
        System.out.println("SurveyDTO = " + surveyDTO);
        surveyService.save(surveyDTO);

        return "index";
    }
    */

    // 설문조사 작성하고 db에 저장하기(로그인했을 때 id 가져와서->여기 session에서 값 못 가져오는 문제 있음)
    @PostMapping(value="/member/survey")
    public SurveyDTO setSurveyDTO(@RequestBody SurveyDTO surveyDTO){

        System.out.println(surveyDTO);
        surveyService.save(surveyDTO);

        // 설문조사 끝나자 마자 최근 일주일 간의 결제내역 분석결과를 기반으로 미션 생성 (payment 객체 불러와서 AA table에 분석 결과가 저장되어있는 상태에서)

        // 미션, 피드백 문장을 만들기 위한 [ 해당 회원 id / 미션, 피드백 시작 날짜 정보 ]
        String selectedMemberId = surveyDTO.getSurveyId();
        String startDate = accountAnalyzeService.findThisWeek(selectedMemberId);

        // 1. 미션 로직을 통해 미션 항목 선정
        missionController.mission(selectedMemberId);

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

        // 5. profile save & goalEntry 업데이트
        // survey 최초 한 번만 한다고 가정하고 -> level = 1
        // 나중에 survey 변경할 수 있도록 수정한다면 이 부분 바꿔야 함.
        ProfileDTO profileDTO = new ProfileDTO(selectedMemberId,1,"낭비꾼"); // 나중에 goalEntry 추가
        profileService.updateGoalEntry(profileDTO);

        return surveyDTO;
    }

    // 설문조사 보여주기(로그인했을 때 id 가져와서)
    @RequestMapping("/api/v1/surveydto")
    @ResponseBody
    public SurveyDTO getSurveyDTO(HttpSession session){
        System.out.println("getSurveyDTO");

        String loginId = session.getAttribute("loginId").toString();
        System.out.println("login id = " + loginId);

        SurveyDTO surveydto = surveyService.findBySurveyId(loginId);
        System.out.println(surveydto);

        System.out.println("react connect");
        return surveydto;
    }

    /*
    // 설문조사 작성하고 db에 저장하기(로그인했을 때 id 가져와서->여기 session에서 값 못 가져오는 문제 있음)
    @RequestMapping("/member/surveyDTO")
    public ResponseEntity<String> setSurveyDTO(@RequestBody SurveyDTO surveyDTO){

        System.out.println(surveyDTO);

        //String loginId = surveyService.getLoginId(session);
        //String loginId = surveyDTO.getSurveyId();
        //System.out.println("login id = " + loginId);

        surveyService.save(surveyDTO);
        //log.debug("surveyDTOInfo = {}", surveyDTO.toString());
        return ResponseEntity.ok(surveyDTO.toString());
    }

    */




}
//MemberController.class