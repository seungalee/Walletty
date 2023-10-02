package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.entity.SurveyEntity;
import com.example.demo.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public void save(SurveyDTO surveyDTO) {
        SurveyEntity surveyEntity = SurveyEntity.toSurveyEntity(surveyDTO);
        surveyRepository.save(surveyEntity);
    }

    public SurveyDTO getLatestSurveyDTO() {
        SurveyEntity surveyEntity = surveyRepository.findTopBy();
        SurveyDTO dto = SurveyDTO.toSurveyDTO(surveyEntity);
        return dto;
    }
}
