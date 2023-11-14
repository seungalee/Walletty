package com.example.demo.dto;


import com.example.demo.entity.SurveyLimitEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SurveyLimitDTO {
    private String surveyId;

    private int entry1totalAmount;
    private int entry1totalAmount10;
    private int entry1totalAmount90;

    private int entry2totalAmount;
    private int entry2totalAmount10;
    private int entry2totalAmount90;

    private int entry3totalAmount;
    private int entry3totalAmount10;
    private int entry3totalAmount90;

    public static SurveyLimitDTO toSurveyLimitDTO(SurveyLimitEntity surveyLimitEntity) {
        SurveyLimitDTO surveyLimitDTO = new SurveyLimitDTO();

        surveyLimitDTO.setSurveyId(surveyLimitEntity.getSurveyId());

        surveyLimitDTO.setEntry1totalAmount(surveyLimitEntity.getEntry1totalAmount());
        surveyLimitDTO.setEntry1totalAmount10(surveyLimitEntity.getEntry1totalAmount10());
        surveyLimitDTO.setEntry1totalAmount90(surveyLimitEntity.getEntry1totalAmount90());

        surveyLimitDTO.setEntry2totalAmount(surveyLimitEntity.getEntry2totalAmount());
        surveyLimitDTO.setEntry2totalAmount10(surveyLimitEntity.getEntry2totalAmount10());
        surveyLimitDTO.setEntry2totalAmount90(surveyLimitEntity.getEntry2totalAmount90());

        surveyLimitDTO.setEntry3totalAmount(surveyLimitEntity.getEntry3totalAmount());
        surveyLimitDTO.setEntry3totalAmount10(surveyLimitEntity.getEntry3totalAmount10());
        surveyLimitDTO.setEntry3totalAmount90(surveyLimitEntity.getEntry3totalAmount90());

        return surveyLimitDTO;
    }
}
