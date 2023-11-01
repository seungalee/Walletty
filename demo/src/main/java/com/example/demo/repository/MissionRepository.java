package com.example.demo.repository;

import com.example.demo.entity.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<MissionEntity, String> {
    List<MissionEntity> findByMemberId(String memberId);
    Optional<MissionEntity> findByMemberIdAndStartdate(String memberId, String startdate);

    //Optional<MissionEntity> findByMemberIdAndNow(String memberId, String now);
}
