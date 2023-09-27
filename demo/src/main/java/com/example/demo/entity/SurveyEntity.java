package com.example.demo.entity;

import com.example.demo.dto.SurveyDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "survey_table")
public class SurveyEntity {

    @Id
    private String surveyId;

    @Column(unique = true)
    private String fixed_entry;

    @Column(nullable = false)
    private String goal_entry_1;
    @Column(nullable = false)
    private String goal_entry_2;
    @Column(nullable = false)
    private String goal_entry_3;

    @Column(nullable = false)
    private int goal_money_1;
    @Column(nullable = false)
    private int goal_money_2;
    @Column(nullable = false)
    private int goal_money_3;


    public static SurveyEntity toSurveyEntity(SurveyDTO surveyDTO){
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setSurveyId(surveyDTO.getSurveyId());
        surveyEntity.setFixed_entry(surveyDTO.getFixed_entry());

        surveyEntity.setGoal_entry_1(surveyDTO.getGoal_entry_1());
        surveyEntity.setGoal_entry_2(surveyDTO.getGoal_entry_2());
        surveyEntity.setGoal_entry_3(surveyDTO.getGoal_entry_3());

        surveyEntity.setGoal_money_1(surveyDTO.getGoal_money_1());
        surveyEntity.setGoal_money_2(surveyDTO.getGoal_money_2());
        surveyEntity.setGoal_money_3(surveyDTO.getGoal_money_3());

        return surveyEntity;
    }

}