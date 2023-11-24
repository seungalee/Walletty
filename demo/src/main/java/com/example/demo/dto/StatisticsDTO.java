package com.example.demo.dto;

import com.example.demo.entity.StatisticsEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StatisticsDTO {
    private Long id;
    private String memberId;
    private String entry;
    private double rate;
//    private double eatoutRate;
//    private double cafeRate;
//    private double deliverRate;
//    private double shoppingRate;
//    private double taxiRate;
//    private double beautyRate;
//    private double snackRate;
    private String week;

    public static StatisticsDTO toStatisticsDTO(StatisticsEntity statisticsEntity) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setMemberId(statisticsEntity.getMemberId());
        statisticsDTO.setEntry(statisticsEntity.getEntry());
        statisticsDTO.setRate(statisticsEntity.getRate());
//        statisticsDTO.setEatoutRate(statisticsEntity.getEatoutRate());
//        statisticsDTO.setCafeRate(statisticsEntity.getCafeRate());
//        statisticsDTO.setDeliverRate(statisticsEntity.getDeliverRate());
//        statisticsDTO.setShoppingRate(statisticsEntity.getShoppingRate());
//        statisticsDTO.setTaxiRate(statisticsEntity.getTaxiRate());
//        statisticsDTO.setBeautyRate(statisticsEntity.getBeautyRate());
//        statisticsDTO.setSnackRate(statisticsEntity.getSnackRate());
        statisticsDTO.setWeek(statisticsEntity.getWeek());
        return statisticsDTO;
    }

    public StatisticsDTO(String memberId, String entry, double rate, String week){
        this.memberId = memberId;
        this.entry = entry;
        this.rate = rate;
//        this.eatoutRate = 0.0;
//        this.beautyRate = 0.0;
//        this.cafeRate = 0.0;
//        this.deliverRate = 0.0;
//        this.shoppingRate = 0.0;
//        this.snackRate = 0.0;
//        this.taxiRate = 0.0;
        this.week = week;
    }
}
