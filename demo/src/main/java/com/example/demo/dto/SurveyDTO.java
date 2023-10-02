package com.example.demo.dto;

import com.example.demo.entity.SurveyEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SurveyDTO {
    private String surveyId;

    private String fixed_entry;

    private String goal_entry_1;
    private String goal_entry_2;
    private String goal_entry_3;

    private int goal_money_1;
    private int goal_money_2;
    private int goal_money_3;


    public static SurveyDTO toSurveyDTO(SurveyEntity surveyEntity){
        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setSurveyId(surveyEntity.getSurveyId());
        surveyDTO.setFixed_entry(surveyEntity.getFixed_entry());

        surveyDTO.setGoal_entry_1(surveyEntity.getGoal_entry_1());
        surveyDTO.setGoal_entry_2(surveyEntity.getGoal_entry_2());
        surveyDTO.setGoal_entry_3(surveyEntity.getGoal_entry_3());

        surveyDTO.setGoal_money_1(surveyEntity.getGoal_money_1());
        surveyDTO.setGoal_money_2(surveyEntity.getGoal_money_2());
        surveyDTO.setGoal_money_3(surveyEntity.getGoal_money_3());

        return surveyDTO;
    }
}
