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

    @Column
    private String fixedEntry;

    @Column(nullable = false)
    private String goalEntry1;
    @Column(nullable = false)
    private String goalEntry2;
    @Column(nullable = false)
    private String goalEntry3;

    @Column(nullable = false)
    private int goalMoney1;
    @Column(nullable = false)
    private int goalMoney2;
    @Column(nullable = false)
    private int goalMoney3;


    public static SurveyEntity toSurveyEntity(String surveyId, SurveyDTO surveyDTO){
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setSurveyId(surveyId);
        surveyEntity.setFixedEntry(surveyDTO.getFixedEntry());

        surveyEntity.setGoalEntry1(surveyDTO.getGoalEntry1());
        surveyEntity.setGoalEntry2(surveyDTO.getGoalEntry2());
        surveyEntity.setGoalEntry3(surveyDTO.getGoalEntry3());

        surveyEntity.setGoalMoney1(surveyDTO.getGoalMoney1());
        surveyEntity.setGoalMoney2(surveyDTO.getGoalMoney2());
        surveyEntity.setGoalMoney3(surveyDTO.getGoalMoney3());

        return surveyEntity;
    }

}