package com.example.demo.entity;

import com.example.demo.dto.SafeDTO;
import com.example.demo.dto.StatisticsDTO;
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
    private String entry;

    @Column
    private double rate;

//    @Column
//    private double eatoutRate;
//
//    @Column
//    private double cafeRate;
//
//    @Column
//    private double deliverRate;
//
//    @Column
//    private double shoppingRate;
//
//    @Column
//    private double taxiRate;
//
//    @Column
//    private double beautyRate;
//
//    @Column
//    private double snackRate;

    @Column
    private String week;

    public static StatisticsEntity toStatisticsEntity(StatisticsDTO statisticsDTO){
        StatisticsEntity statisticsEntity = new StatisticsEntity();
        statisticsEntity.setMemberId(statisticsDTO.getMemberId());
        statisticsEntity.setEntry(statisticsDTO.getEntry());
        statisticsEntity.setRate(statisticsDTO.getRate());
        statisticsEntity.setWeek(statisticsDTO.getWeek());
        return statisticsEntity;
    }
}
