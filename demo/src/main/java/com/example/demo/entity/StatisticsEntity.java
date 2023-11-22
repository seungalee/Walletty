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
@Table(name = "statistics_table")
public class StatisticsEntity {
    @Id
    private String memberId;

    @Column
    private Integer eatoutRate;

    @Column
    private Integer cafeRate;

    @Column
    private Integer deliverRate;

    @Column
    private Integer shoppingRate;

    @Column
    private Integer taxiRate;

    @Column
    private Integer beautyRate;

    @Column
    private Integer snackRate;

    @Column
    private String week;
}
