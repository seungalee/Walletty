package com.example.demo.entity;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "feedback_table")
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    @Column
    private String memberId;

    @Column
    private String content;

    @Column
    private String comment; //후보

    @Column
    private String sendFront;

    @Column
    private String okToSend;

    public static FeedbackEntity toFeedbackEntity(FeedbackDTO feedbackDTO){
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setFeedbackId(feedbackDTO.getFeedbackId());
        feedbackEntity.setMemberId(feedbackDTO.getMemberId());
        feedbackEntity.setContent(feedbackDTO.getContent());
        feedbackEntity.setComment(feedbackDTO.getComment());
        feedbackEntity.setSendFront(feedbackDTO.getSendFront());
        feedbackEntity.setOkToSend(feedbackDTO.getOkToSend());

        return feedbackEntity;
    }
}
