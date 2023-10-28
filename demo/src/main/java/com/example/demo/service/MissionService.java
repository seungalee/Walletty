package com.example.demo.service;

import com.example.demo.dto.MissionDTO;
import com.example.demo.entity.MissionEntity;
import com.example.demo.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    //String memberId, String missionEntry, int missionMoney 저장
    public void save(MissionDTO missionDTO) {
        MissionEntity missionEntity = MissionEntity.toMissionEntity(missionDTO);
        missionRepository.save(missionEntity);

    }

}
