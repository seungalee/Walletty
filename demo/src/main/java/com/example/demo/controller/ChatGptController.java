package com.example.demo.controller;

import com.example.demo.dto.ChatGptResponse;
import com.example.demo.dto.QuestionRequest;
import com.example.demo.service.ChatGptService;
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
        return chatGptResponse;
        //return apiResponse.getResponseEntity(locale, code,
        //        chatGptResponse != null ? chatGptResponse.getChoices().get(0).getMessage().getContent() : new ChatGptResponse());
    }
}