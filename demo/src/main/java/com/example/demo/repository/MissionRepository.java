package com.example.demo.repository;

import com.example.demo.entity.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<MissionEntity, String> {
    Optional<MissionEntity> findByMemberId(String memberId);
}
