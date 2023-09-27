package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.service.MemberService;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/member/login") // session : 로그인 유지
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }

    private final SurveyService surveyService;

    @GetMapping("/member/survey")
    public String surveyForm() {
        return "survey";
    }

    @PostMapping("/member/survey")
    public String survey(@ModelAttribute SurveyDTO surveyDTO) {
        System.out.println("MemberController.survey");
        System.out.println("SurveyDTO = " + surveyDTO);
        surveyService.save(surveyDTO);

        return "login";
    }
}
//MemberController.class