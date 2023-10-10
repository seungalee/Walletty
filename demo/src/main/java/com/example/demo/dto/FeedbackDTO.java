package com.example.demo.dto;

import com.example.demo.entity.FeedbackEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FeedbackDTO {
    private long feedbackId;
    private String memberId;
    private String content;
    private String comment; //피드백 코멘트 후보
    private String sendFront;

    public static FeedbackDTO toFeedbackDTO(FeedbackEntity feedbackEntity){
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedbackEntity.getFeedbackId());
        feedbackDTO.setMemberId(feedbackEntity.getMemberId());
        feedbackDTO.setContent(feedbackEntity.getContent());
        feedbackDTO.setComment(feedbackEntity.getComment());
        feedbackDTO.setSendFront(feedbackEntity.getSendFront());
        return feedbackDTO;
    }
}
