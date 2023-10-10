package com.example.demo.entity;

import com.example.demo.dto.FeedbackCommentDTO;
import com.example.demo.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "feedback_comment_table") //database에 해당 이름의 테이블 생성
public class FeedbackCommentEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column(unique = true)
    private String comment;

    public static FeedbackCommentEntity toFeedbackCommentEntity(FeedbackCommentDTO feedbackCommentDTO){
        FeedbackCommentEntity feedbackCommentEntity = new FeedbackCommentEntity();
        feedbackCommentEntity.setId(feedbackCommentDTO.getId());
        feedbackCommentEntity.setComment(feedbackCommentDTO.getComment());
        return feedbackCommentEntity;
    }

}
