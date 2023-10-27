package com.example.demo.controller;

import com.example.demo.dto.ChatGptResponse;
import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.QuestionRequest;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.service.ChatGptService;
import com.example.demo.service.FeedbackCommentService;
import com.example.demo.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
@RestController
public class ChatGptController {
    //private final APIResponse apiResponse;
    private final ChatGptService chatGptService;

    private final FeedbackService feedbackService;

    private final FeedbackCommentService feedbackCommentService;

    //@Operation(summary = "Question to Chat-GPT")
    @PostMapping("/question")
    public ChatGptResponse sendQuestion(
            Locale locale,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody QuestionRequest questionRequest) {
        //String code = ResponseCode.CD_SUCCESS;
        ChatGptResponse chatGptResponse = null;
        try {
            chatGptResponse = chatGptService.askQuestion(questionRequest);
        } catch (Exception e) {
            //apiResponse.printErrorMessage(e);
            //code = e.getMessage();
        }
        String content = chatGptResponse.getChoices().get(0).getMessage().getContent();
        feedbackService.save(content);
        return chatGptResponse;
        //return apiResponse.getResponseEntity(locale, code, chatGptResponse != null ? chatGptResponse.getChoices().get(0).getMessage().getContent() : new ChatGptResponse());
    }

    @PostMapping("/feedback") // 프론트에서 회원 id와 함께 피드백 요청하면 해당 회원의 피드백 테이블에 아직 안 보낸 피드백을 골라서 넘겨줌.
    public String sendFeedback(@RequestBody MemberDTO memberDTO){ // /feedback에서 받은 회원 정보(id만??)로 피드백 보내주기
        FeedbackDTO feedbackDTO = feedbackService.findByMemberIdAndOkToSend(memberDTO.getMemberId(),"false");
        return "{\"sendFront\" : \"" + feedbackDTO.getSendFront() + "\"}"; // Json형식으로 feedback String 리턴
        // return feedbackDTO.getSendFront();
    }


}