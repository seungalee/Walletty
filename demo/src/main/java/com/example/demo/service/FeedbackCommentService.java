package com.example.demo.service;

import com.example.demo.dto.FeedbackCommentDTO;
import com.example.demo.entity.FeedbackCommentEntity;
import com.example.demo.repository.FeedbackCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class FeedbackCommentService {
    private final FeedbackCommentRepository feedbackCommentRepository; // 먼저 jpa, mysql dependency 추가

    public void save() {
        // repository의 save 메서드 호출
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
            FeedbackCommentEntity feedbackCommentEntity = FeedbackCommentEntity.toFeedbackCommentEntity(comment);
            feedbackCommentRepository.save(feedbackCommentEntity);
            //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
        }
    }
}
