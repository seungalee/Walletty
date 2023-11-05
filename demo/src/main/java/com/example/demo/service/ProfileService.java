package com.example.demo.service;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.MissionEntity;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class ProfileService {
    private final ProfileRepository profileRepository; // 먼저 jpa, mysql dependency 추가
    private final AccountAnalyzeRepository accountAnalyzeRepository;
    private final MissionRepository missionRepository;

    public void updateGoalEntry(ProfileDTO profileDTO) { // survey : update goalEntry

        Optional<ProfileEntity> profile = profileRepository.findByMemberId(profileDTO.getMemberId());

        if (profile.isPresent()){
            ProfileEntity originEntity = profile.get();
            //String updateEntry = profileDTO.getGoalEntry();
            //originEntity.setGoal(updateEntry);
        }
        else {
            // repository의 save 메서드 호출
            ProfileEntity profileEntity = ProfileEntity.toProfileEntity(profileDTO);
            profileRepository.save(profileEntity);
            //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
        }
    }

    public void updateWeekTotalAmount(String memberId) { // payment 들고올 때 weekTotalAmount

        Optional<ProfileEntity> profile = profileRepository.findByMemberId(memberId);

        if (profile.isPresent()){ // 무조건 처음에 updateGoalEntry를 통해 해당 멤버아이디의 프로필이 만들어져있는 상태임! 이미 있으니 해당 컬럼만 update.
            ProfileEntity originEntity = profile.get();
            List<AccountAnalyzeEntity> weekTotalAmount = accountAnalyzeRepository.findByMemberIdAndOkToUse(memberId,false);
            Integer updateAmount = 0;
            for (AccountAnalyzeEntity acc : weekTotalAmount){
                updateAmount += acc.getTotalAmount();
            }
            originEntity.setWeekTotalAmount(updateAmount);
        }
    }

    public void updateMission(int missionId) { // (진행중인) 미션 생긴 경우 -> missionCnt

        Optional<MissionEntity> mission = missionRepository.findByMissionId(missionId);
        Optional<ProfileEntity> profile = profileRepository.findByMemberId(mission.get().getMemberId());

        if (profile.isPresent()){
            ProfileEntity originEntity = profile.get();
            Integer updateCnt = originEntity.getMissionCnt() + 1;
            originEntity.setMissionCnt(updateCnt);
        }
    }
    public void updateSuccess(int missionId) { // 미션 성공 or 실패 한 경우 -> successCnt, level, position, successMission

        Optional<MissionEntity> mission = missionRepository.findByMissionId(missionId);
        Optional<ProfileEntity> profile = profileRepository.findByMemberId(mission.get().getMemberId());

        if (profile.isPresent()){
            ProfileEntity originEntity = profile.get();
            Integer updateCnt = originEntity.getSuccessCnt() + 1; // 데모를 위해 무조건 success만 했다고 가정
            originEntity.setSuccessCnt(updateCnt);

            Integer updateLevel = originEntity.getLevel() + 1;
            originEntity.setLevel(updateLevel);

            String updatePosition;
            if(updateLevel > 0 && updateLevel < 6){ // 1~5
                updatePosition = "낭비꾼";
            } else if (updateLevel > 5 && updateLevel < 11) { // 6~10
                updatePosition = "절약 초보";
            } else if (updateLevel > 10 && updateLevel < 16) { // 11~15
                updatePosition = "절약 중수";
            } else if (updateLevel > 15 && updateLevel < 21) { // 16~20
                updatePosition = "절약 고수";
            } else{ // 21~25
                updatePosition = "부자";
            }
            originEntity.setPosition(updatePosition);

            //originEntity.setSuccessMission(); //나중에 추가하기
        }
    }

}
