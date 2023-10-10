package com.example.demo.controller;

import com.example.demo.dto.FeedbackCommentDTO;
import com.example.demo.service.FeedbackCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class FeedbackCommentController {
    private final FeedbackCommentService feedbackCommentService;

    @RequestMapping("/saveFeedbackComment")
    public void saveFeedbackComment(){
        // List feedbackCommentDTOList = new ArrayList();
        // FeedbackCommentDTO feedbackCommentDTO = new FeedbackCommentDTO();
        // feedbackCommentDTOList.add(feedbackCommentDTO);
        ArrayList<FeedbackCommentDTO> feedbackCommentDTOList = new ArrayList<FeedbackCommentDTO>();

        feedbackCommentDTOList.add(new FeedbackCommentDTO(1,"돈이 하늘에서 떨어지니?"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(2,"절약은 미래에 대한 투자야!"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(3,"돈이 그렇게 넘쳐나니?"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(4,"꼭 썼어야 했니?"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(5,"돈을 이렇게 낭비하면 못 써."));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(6,"돈은 무한으로 샘솟지 않아!"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(7,"돈을 길바닥에 버리고 다니는거니!"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(8,"돈은 그냥 생겨나지 않아."));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(9,"돈을 아무렇게나 쓰면 나중에 후회할 수 있어."));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(10,"에구 아까워라!"));
        feedbackCommentDTOList.add(new FeedbackCommentDTO(11,"돈을 여기에 너무 자주 쓰는 것 같아! "));

        //List<FeedbackCommentDTO> feedbackCommentDTOList; // 값 넣기.
        for(FeedbackCommentDTO comment : feedbackCommentDTOList){
            feedbackCommentService.save(comment);
        }
        //return FeedbackCommentDTO;
    }
}
