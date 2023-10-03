package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.entity.SurveyEntity;
import com.example.demo.service.MemberService;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")    // name값을 requestparam에 담아온다
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);

        return "login";
    }

    @GetMapping("/member/join")  // /join에서 받은 회원가입 정보를 /member/join에서 받아오기
    public ResponseEntity join(@RequestBody MemberDTO memberDTO){
        memberService.save(memberDTO);
        //log.debug("joinInfo = {}", memberDTO.toString());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/member/login") // session : 로그인 유지
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginId", loginResult.getMemberId());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }

    @GetMapping("/member/loginn")  // /login에서 받은 로그인 정보를 /member/login에서 받아오기
    public ResponseEntity loginn(@RequestBody MemberDTO memberDTO){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            return new ResponseEntity<>("success", HttpStatus.OK);
            //} else if () {  // 회원가입 후 첫 로그인일 때 == survey table에 해당 id를 가진 회원의 정보가 없을 때

        } else {
            // login 실패
            return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping("/api/v1/member")
    @ResponseBody
    public MemberDTO postMember() { //member정보 보내기
        MemberDTO loginResult = memberService.postMember();
        return loginResult;
    }

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


    // 설문조사 작성하고 db에 저장하기(로그인했을 때 id 가져와서->여기 session에서 값 못 가져오는 문제 있음)
    @RequestMapping("/member/surveyDTO")
    public ResponseEntity<String> setSurveyDTO(@RequestBody SurveyDTO surveyDTO){

        System.out.println(surveyDTO);

        //String loginId = surveyService.getLoginId(session);
        String loginId = surveyDTO.getSurveyId();
        System.out.println("login id = " + loginId);

        surveyService.save(loginId, surveyDTO);
        log.debug("surveyDTOInfo = {}", surveyDTO.toString());
        return ResponseEntity.ok(surveyDTO.toString());
    }

}
//MemberController.class