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
    private String content; //chat-gpt에게 얻은 피드백 문장
    private String comment; //피드백 코멘트 후보
    private String sendFront; //최종 피드백 문장 (comment + content)
    private String startdate; //미션 startdate와 동일. (front에서 미션과 피드백 매칭을 위한 것임.)
    private String okToSend; //이미 해 준 피드백인지 여부. false : 이번주 피드백, true : 이전 주차 피드백들

    public static FeedbackDTO toFeedbackDTO(FeedbackEntity feedbackEntity){
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedbackEntity.getFeedbackId());
        feedbackDTO.setMemberId(feedbackEntity.getMemberId());
        feedbackDTO.setContent(feedbackEntity.getContent());
        feedbackDTO.setComment(feedbackEntity.getComment());
        feedbackDTO.setSendFront(feedbackEntity.getSendFront());
        feedbackDTO.setStartdate(feedbackEntity.getStartdate());
        feedbackDTO.setOkToSend(feedbackEntity.getOkToSend());
        return feedbackDTO;
    }
}
