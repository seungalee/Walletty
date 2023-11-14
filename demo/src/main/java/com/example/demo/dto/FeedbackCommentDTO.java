package com.example.demo.dto;

import com.example.demo.entity.FeedbackCommentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FeedbackCommentDTO {
    public Integer id;
    public String comment;

    public FeedbackCommentDTO(Integer id, String comment) {
        // 전역 변수에 인풋으로 들어온 변수값 매핑
        this.id = id;
        this.comment = comment;
    }
}
