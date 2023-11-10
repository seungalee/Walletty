package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "survey_limit_table")
public class SurveyLimitEntity {
    @Id
    private String surveyId;

    @Column
    private int entry1totalAmount;
    @Column
    private int entry1totalAmount10;
    @Column
    private int entry1totalAmount90;

    @Column
    private int entry2totalAmount;
    @Column
    private int entry2totalAmount10;
    @Column
    private int entry2totalAmount90;

    @Column
    private int entry3totalAmount;
    @Column
    private int entry3totalAmount10;
    @Column
    private int entry3totalAmount90;
}
