package com.example.demo.service;

import com.example.demo.dto.SurveyLimitDTO;
import com.example.demo.entity.SurveyLimitEntity;
import com.example.demo.repository.SurveyLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyLimitService {

    private final SurveyLimitRepository surveyLimitRepository;

    public SurveyLimitDTO save(String surveyId, int entry1totalAmount, int entry2totalAmount, int entry3totalAmount) {
        SurveyLimitEntity surveyLimitEntity = new SurveyLimitEntity();
        surveyLimitEntity.setSurveyId(surveyId);

        int moneyCut100;
        surveyLimitEntity.setEntry1totalAmount(entry1totalAmount);
        moneyCut100 = ((int)(entry1totalAmount * 0.1) / 100) * 100;
        surveyLimitEntity.setEntry1totalAmount10(moneyCut100);
        moneyCut100 = ((int)(entry1totalAmount * 0.9) / 100) * 100;
        surveyLimitEntity.setEntry1totalAmount90(moneyCut100);

        surveyLimitEntity.setEntry2totalAmount(entry2totalAmount);
        moneyCut100 = ((int)(entry2totalAmount * 0.1) / 100) * 100;
        surveyLimitEntity.setEntry2totalAmount10(moneyCut100);
        moneyCut100 = ((int)(entry2totalAmount * 0.9) / 100) * 100;
        surveyLimitEntity.setEntry2totalAmount90(moneyCut100);

        surveyLimitEntity.setEntry3totalAmount(entry3totalAmount);
        moneyCut100 = ((int)(entry3totalAmount * 0.1) / 100) * 100;
        surveyLimitEntity.setEntry3totalAmount10(moneyCut100);
        moneyCut100 = ((int)(entry3totalAmount * 0.9) / 100) * 100;
        surveyLimitEntity.setEntry3totalAmount90(moneyCut100);

        surveyLimitRepository.save(surveyLimitEntity);
        SurveyLimitDTO surveyLimitDTO = SurveyLimitDTO.toSurveyLimitDTO(surveyLimitEntity);
        return surveyLimitDTO;

    }

}
