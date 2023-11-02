package com.example.demo.dto;

import com.example.demo.entity.MissionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MissionDTO {
    private int missionId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String missionEntry;
    private int missionMoney;
    private String missionSen;
    private String now;

    public static MissionDTO toMissionDTO(MissionEntity missionEntity){
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setMissionId(missionEntity.getMissionId());
        missionDTO.setMemberId(missionEntity.getMemberId());

        missionDTO.setStartDate(missionEntity.getStartDate());
        missionDTO.setEndDate(missionEntity.getEndDate());

        missionDTO.setMissionEntry(missionEntity.getMissionEntry());
        missionDTO.setMissionMoney(missionEntity.getMissionMoney());

        missionDTO.setMissionSen(missionEntity.getMissionSen());
        missionDTO.setNow(missionEntity.getNow());

        return missionDTO;
    }
}
