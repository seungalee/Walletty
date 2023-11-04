package com.example.demo.service;

import com.example.demo.entity.SafeEntity;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SafeService {
    private final SafeRepository safeRepository;

    private final MissionRepository missionRepository;
    private final AccountAnalyzeRepository accountAnalyzeRepository;

    public void save(int missionId) {

        SafeEntity safeEntity = new SafeEntity();
        safeEntity.setMissionId(missionId);
        safeEntity.setInSafe(true);
        safeEntity.setOutSafe(false);

        safeRepository.save(safeEntity);

    }
}
