package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.ChatGptService;
import com.example.demo.service.FeedbackCommentService;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.MissionService;
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
    //private final APIResponse apiResponse;
    private final ChatGptService chatGptService;

    private final FeedbackService feedbackService;
    private final MemberRepository memberRepository;

    private final MissionService missionService;


    //@Operation(summary = "Question to Chat-GPT")
    @RequestMapping("/question")
    public void sendQuestion(@RequestBody MemberDTO memberDTO) {
        //String code = ResponseCode.CD_SUCCESS;
        ChatGptResponse chatGptResponse = null;
        //try {
            // 여기에 id 관련 추가
        String selectedMemberId = memberDTO.getMemberId();
        String missionDate = "0908"; //여기 입력 그때그때 바꾸기. 원래는 오늘 date로 해야하지만 우리는 가상 결제내역이니까 이렇게.
        //List<MemberEntity> memberAll = memberRepository.findAll();
        //for (MemberEntity mem : memberAll) { //너무 많이 돌아감.
        //    selectedMemberId = mem.getMemberId();
            chatGptResponse = chatGptService.askQuestion(selectedMemberId, missionDate);

        //} catch (Exception e) {
            //apiResponse.printErrorMessage(e);
            //code = e.getMessage();
        //}
        String content = chatGptResponse.getChoices().get(0).getMessage().getContent();
        // 여기에 id 관련 추가
        feedbackService.save(selectedMemberId, content);
        //}
        //return chatGptResponse;
        //return apiResponse.getResponseEntity(locale, code, chatGptResponse != null ? chatGptResponse.getChoices().get(0).getMessage().getContent() : new ChatGptResponse());
    }

    @RequestMapping("/questionM")
    public void sendQuestionM(@RequestBody MemberDTO memberDTO){
        ChatGptResponse chatGptResponse = null;

        String selectedMemberId = memberDTO.getMemberId();
        String missionDate = "0908";

        chatGptResponse = chatGptService.askQuestionM(selectedMemberId, missionDate);

        String content = chatGptResponse.getChoices().get(0).getMessage().getContent();
        System.out.println(content);
        // 여기에 id 관련 추가
        missionService.saveMissionSen(selectedMemberId, missionDate, content);
    }

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