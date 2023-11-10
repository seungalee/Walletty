package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
@RestController
public class ChatGptController {
    private final FeedbackService feedbackService;
    private final MissionService missionService;

    @PostMapping("/feedback") // 프론트에서 회원 id와 함께 피드백 요청하면 해당 회원의 피드백 테이블에 아직 안 보낸 피드백을 골라서 넘겨줌.
    public List<FeedbackDTO> sendFeedback(@RequestBody MemberDTO memberDTO){ // /feedback에서 받은 회원 정보(id만??)로 피드백 보내주기
        // FeedbackDTO feedbackDTO = feedbackService.findByMemberIdAndOkToSend(memberDTO.getMemberId(),"false");
        List<FeedbackDTO> feedbackDTO = feedbackService.findByMemberId(memberDTO.getMemberId());
        return feedbackDTO; // FeedbackDTO 타입의 list를 리턴
        // return "{\"sendFront\" : \"" + feedbackDTO.getSendFront() + "\"}"; // Json형식으로 feedback "String" 리턴
    }

    @PostMapping("/mission") // 프론트에서 회원 id와 함께 미션 요청하면 해당 회원의 미션 테이블에서 현재 미션을 골라서 넘겨줌.
    public List<MissionDTO> sendMission(@RequestBody MemberDTO memberDTO){
        List<MissionDTO> missionDTO = missionService.findByMemberId(memberDTO.getMemberId());
        //return "{\"missionSen\" : \"" + missionDTO.getMissionSen() + "\"}";
        return missionDTO;
    }


}