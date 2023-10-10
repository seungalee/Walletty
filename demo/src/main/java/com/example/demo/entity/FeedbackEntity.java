package com.example.demo.entity;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "feedback_table")
public class FeedbackEntity {

    @Id
    private long feedbackId;

    @Column
    private String memberId;

    @Column
    private String content;

    @Column
    private String cand; //후보

    @Column
    private String sendFront;

    public static FeedbackEntity toFeedbackEntity(FeedbackDTO feedbackDTO){
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setFeedbackId(feedbackDTO.getFeedbackId());
        feedbackEntity.setMemberId(feedbackDTO.getMemberId());
        feedbackEntity.setContent(feedbackDTO.getContent());
        feedbackEntity.setCand(feedbackDTO.getCand());
        feedbackEntity.setSendFront(feedbackDTO.getSendFront());

        return feedbackEntity;
    }
}
