package com.example.demo.service;

import com.example.demo.entity.MissionEntity;
import com.example.demo.entity.SafeEntity;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SafeService {
    private final SafeRepository safeRepository;

    private final MissionRepository missionRepository;
    private final AccountAnalyzeRepository accountAnalyzeRepository;

    public void saveMissionId(int missionId) {

        SafeEntity safeEntity = new SafeEntity();
        safeEntity.setMissionId(missionId);
        safeEntity.setInSafe(false);
        safeEntity.setOutSafe(false);

        safeRepository.save(safeEntity);

    }

    public void updateInSafe(int missionId) {
        Optional<SafeEntity> isSafeEntity = safeRepository.findByMissionId(missionId);
        if (isSafeEntity.isPresent()) {
            SafeEntity safeEntity = isSafeEntity.get();
            safeEntity.setInSafe(true);

            safeRepository.save(safeEntity);
        } else {
            System.out.println("금고 테이블에 missionId가 없음. DB 다시 확인");
        }
    }

    public void updateOutSafe(int missionId) {
        Optional<SafeEntity> isSafeEntity = safeRepository.findByMissionId(missionId);
        if (isSafeEntity.isPresent()) {
            SafeEntity safeEntity = isSafeEntity.get();
            safeEntity.setOutSafe(true);

            safeRepository.save(safeEntity);
        } else {
            System.out.println("금고 테이블에 missionId가 없음. DB 다시 확인");
        }
    }
}
