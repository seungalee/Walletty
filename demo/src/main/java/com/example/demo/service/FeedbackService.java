package com.example.demo.service;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public void save(FeedbackDTO feedbackDTO) {
        FeedbackEntity feedbackEntity = FeedbackEntity.toFeedbackEntity(feedbackDTO);
        feedbackRepository.save(feedbackEntity);

    }

    public void saveContent(String content) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setContent(content);
        feedbackRepository.save(feedbackEntity);

    }
}
