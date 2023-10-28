package com.example.demo.entity;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.MissionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "mission_table")
public class MissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int missionId;
    @Column
    private String memberId;
    @Column
    private String startdate;
    @Column
    private String enddate;
    @Column
    private String missionEntry;
    @Column
    private int missionMoney;
    @Column
    private String missionSen;
    @Column
    private String now;

    public static MissionEntity toMissionEntity(MissionDTO missionDTO){
        MissionEntity missionEntity = new MissionEntity();
        missionEntity.setMissionId(missionDTO.getMissionId());
        missionEntity.setMemberId(missionDTO.getMemberId());
        missionEntity.setStartdate(missionDTO.getStartdate());
        missionEntity.setEnddate(missionDTO.getEnddate());
        missionEntity.setMissionEntry(missionDTO.getMissionEntry());
        missionEntity.setMissionMoney(missionDTO.getMissionMoney());
        missionEntity.setMissionSen(missionDTO.getMissionSen());
        missionEntity.setNow(missionDTO.getNow());
        return missionEntity;
    }
}
