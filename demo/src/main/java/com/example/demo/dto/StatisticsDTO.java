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
    private String memberId;
    private Integer eatoutRate;
    private Integer cafeRate;
    private Integer deliverRate;
    private Integer shoppingRate;
    private Integer taxiRate;
    private Integer beautyRate;
    private Integer snackRate;
    private String week;

    public static StatisticsDTO toStatisticsDTO(StatisticsEntity statisticsEntity) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setMemberId(statisticsEntity.getMemberId());
        statisticsDTO.setEatoutRate(statisticsEntity.getEatoutRate());
        statisticsDTO.setCafeRate(statisticsEntity.getCafeRate());
        statisticsDTO.setDeliverRate(statisticsEntity.getDeliverRate());
        statisticsDTO.setShoppingRate(statisticsEntity.getShoppingRate());
        statisticsDTO.setTaxiRate(statisticsEntity.getTaxiRate());
        statisticsDTO.setBeautyRate(statisticsEntity.getBeautyRate());
        statisticsDTO.setSnackRate(statisticsEntity.getSnackRate());
        statisticsDTO.setWeek(statisticsEntity.getWeek());
        return statisticsDTO;
    }
}
