package com.example.demo.dto;


import com.example.demo.entity.ProfileEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProfileDTO {
    private String memberId;
    private Integer missionCnt;
    private Integer successCnt;
    private Integer level;
    private String position;
    private Integer weekTotalAmount; // 이번주에 소비한 금액
    private String goalEntry1;
    private String goalEntry2;
    private String goalEntry3;
    private String successMission1; // 제일 최근에 성공한 미션
    private String successMission2;
    private String successMission3;
    private Integer totalSavingMoney; // 지금까지 절약한 총 금액


    public static ProfileDTO toProfileDTO(ProfileEntity profileEntity){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setMemberId(profileEntity.getMemberId());
        profileDTO.setMissionCnt(profileEntity.getMissionCnt());
        profileDTO.setSuccessCnt(profileEntity.getSuccessCnt());
        profileDTO.setLevel(profileEntity.getLevel());
        profileDTO.setPosition(profileEntity.getPosition());
        profileDTO.setGoalEntry1(profileEntity.getGoalEntry1());
        profileDTO.setGoalEntry2(profileEntity.getGoalEntry2());
        profileDTO.setGoalEntry3(profileEntity.getGoalEntry3());
        profileDTO.setWeekTotalAmount(profileEntity.getWeekTotalAmount());
        profileDTO.setSuccessMission1(profileEntity.getSuccessMission1());
        profileDTO.setSuccessMission2(profileEntity.getSuccessMission2());
        profileDTO.setSuccessMission3(profileEntity.getSuccessMission3());
        profileDTO.setTotalSavingMoney(profileEntity.getTotalSavingMoney());
        return profileDTO;
    }

    public ProfileDTO (String memberId, Integer level, String position, Integer weekTotalAmount){
        this.memberId = memberId;
        this.missionCnt = 0;
        this.successCnt = 0;
        this.level = level;
        this.position = position;
        this.weekTotalAmount = weekTotalAmount;
        this.totalSavingMoney = 0;
    }

    public ProfileDTO (String memberId, Integer level, String position){ //goalEntry 나중에 추가
        this.memberId = memberId;
        this.level = level;
        this.position = position;
    }


}


