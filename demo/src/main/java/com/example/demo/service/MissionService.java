package com.example.demo.service;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.MissionDTO;
import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.FeedbackCommentEntity;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.MissionEntity;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final AccountAnalyzeRepository accountAnalyzeRepository;

    public void save(MissionDTO missionDTO) {
        MissionEntity missionEntity = MissionEntity.toMissionEntity(missionDTO);
        missionRepository.save(missionEntity);

    }

    public void saveMissionSen(String memberId, String startDate, String content) {

        Optional<MissionEntity> ismissionEntity = missionRepository.findByMemberIdAndStartDate(memberId, startDate);
        if(ismissionEntity.isPresent()) {
            MissionEntity missionEntity = ismissionEntity.get();
            missionEntity.setMissionSen(content);
            missionRepository.save(missionEntity);
        }else{
            System.out.println("미션 테이블에 id랑 날짜가 없는데 미션을 줄 수 없음. db 다시 업데이트");
        }

    }

    public void saveAccept(int missionId) {

        Optional<MissionEntity> ismissionEntity = missionRepository.findByMissionId(missionId);
        if(ismissionEntity.isPresent()) {
            MissionEntity missionEntity = ismissionEntity.get();
            missionEntity.setAccept(true);
            missionRepository.save(missionEntity);
        }else{
            System.out.println("미션 테이블에 missionId가 없음. DB 다시 확인");
        }

    }

    /*
    public MissionDTO findByMemberIdAndNow(String memberId, String now) {
        Optional<MissionEntity> byMemberIdNow = missionRepository.findByMemberIdAndNow(memberId,now);
        if(byMemberIdNow.isPresent()) {
            MissionEntity missionEntity = byMemberIdNow.get();
            MissionDTO mDTO = MissionDTO.toMissionDTO(missionEntity);
            return mDTO;
        }else{
            return null;
        }
    }

     */

    public List<MissionDTO> findByMemberId(String memberId) {
        List<MissionEntity> byMemberId = missionRepository.findByMemberId(memberId);
        List<MissionDTO> mDTO = new ArrayList<>();
        for (MissionEntity ent : byMemberId){
            mDTO.add(MissionDTO.toMissionDTO(ent));
        }
        return mDTO;
    }

    public MissionDTO findByStartDate(String startDate) {

        Optional<MissionEntity> ismissionEntity = missionRepository.findByStartDate(startDate);
        if(ismissionEntity.isPresent()) {
            MissionEntity missionEntity = ismissionEntity.get();
            MissionDTO mdto = MissionDTO.toMissionDTO(missionEntity);
            return mdto;
        }
        else{
            return null;
        }
    }

    public AccountAnalyzeEntity changeOkToUseWithMissionEntry(String entry, String memberId, String orderWeek) {
        AccountAnalyzeEntity missionEntity =
                accountAnalyzeRepository.findByEntryAndMemberIdAndOkToUseAndOrderWeek(entry, memberId, false, orderWeek);
        if(missionEntity!=null){
            missionEntity.setOkToUse(true);
            return accountAnalyzeRepository.save(missionEntity);
        }
        else{
            return null;
        }
    }


    public MissionDTO findByMemberIdAndStartDate(String memberId, String startDate) {
        Optional<MissionEntity> ismissionEntity = missionRepository.findByMemberIdAndStartDate(memberId, startDate);
        if(ismissionEntity.isPresent()) {
            MissionEntity missionEntity = ismissionEntity.get();
            MissionDTO mdto = MissionDTO.toMissionDTO(missionEntity);
            return mdto;
        }
        else{
            return null;
        }

    }
}
