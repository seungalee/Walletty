package com.example.demo.dto;

import com.example.demo.entity.SurveyEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SurveyDTO {
    private String surveyId;

    private String fixedEntry;

    private String goalEntry1;
    private String goalEntry2;
    private String goalEntry3;

    private int goalMoney1;
    private int goalMoney2;
    private int goalMoney3;


    public static SurveyDTO toSurveyDTO(SurveyEntity surveyEntity){
        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setSurveyId(surveyEntity.getSurveyId());

        surveyDTO.setFixedEntry(surveyEntity.getFixedEntry());

        surveyDTO.setGoalEntry1(surveyEntity.getGoalEntry1());
        surveyDTO.setGoalEntry2(surveyEntity.getGoalEntry2());
        surveyDTO.setGoalEntry3(surveyEntity.getGoalEntry3());

        surveyDTO.setGoalMoney1(surveyEntity.getGoalMoney1());
        surveyDTO.setGoalMoney2(surveyEntity.getGoalMoney2());
        surveyDTO.setGoalMoney3(surveyEntity.getGoalMoney3());

        return surveyDTO;
    }
}
