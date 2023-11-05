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
    private Integer weekTotalAmount;
//    private List<String> goalEntry; //3개
//    private List<String> successMission;

    public static ProfileDTO toProfileDTO(ProfileEntity profileEntity){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setMemberId(profileEntity.getMemberId());
        profileDTO.setMissionCnt(profileEntity.getMissionCnt());
        profileDTO.setSuccessCnt(profileEntity.getSuccessCnt());
        profileDTO.setLevel(profileEntity.getLevel());
        profileDTO.setPosition(profileEntity.getPosition());
        profileDTO.setWeekTotalAmount(profileEntity.getWeekTotalAmount());
        return profileDTO;
    }

    public ProfileDTO (String memberId, Integer level, String position){ //goalEntry 나중에 추가
        this.memberId = memberId;
        this.level = level;
        this.position = position;
    }


}


