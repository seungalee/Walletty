package com.example.demo.dto;

import com.example.demo.entity.RankingEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RankingDTO {
    private String memberId;
    private Integer level;
    private String position;
    private Integer totalSavingMoney;
    private Integer rank;
    private Boolean isFriend;

    public static RankingDTO toRankingDTO(RankingEntity rankingEntity) {
        RankingDTO rankingDTO = new RankingDTO();
        rankingDTO.setMemberId(rankingEntity.getMemberId());
        rankingDTO.setLevel(rankingEntity.getLevel());
        rankingDTO.setPosition(rankingEntity.getPosition());
        rankingDTO.setTotalSavingMoney(rankingEntity.getTotalSavingMoney());
        rankingDTO.setRank(rankingEntity.getRank());
        rankingDTO.setIsFriend(rankingEntity.getIsFriend());
        return rankingDTO;
    }
}
