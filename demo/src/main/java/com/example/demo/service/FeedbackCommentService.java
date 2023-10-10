package com.example.demo.service;

import com.example.demo.dto.FeedbackCommentDTO;
import com.example.demo.entity.FeedbackCommentEntity;
import com.example.demo.repository.FeedbackCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class FeedbackCommentService {
    private final FeedbackCommentRepository feedbackCommentRepository; // 먼저 jpa, mysql dependency 추가

    public void save(FeedbackCommentDTO feedbackCommentDTO) {
        // repository의 save 메서드 호출
        FeedbackCommentEntity feedbackCommentEntity = FeedbackCommentEntity.toFeedbackCommentEntity(feedbackCommentDTO);
        feedbackCommentRepository.save(feedbackCommentEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }
}
