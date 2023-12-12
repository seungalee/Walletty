package com.example.demo.repository;

import com.example.demo.entity.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository  extends JpaRepository<StatisticsEntity, String> {
    Optional<StatisticsEntity> findByMemberId(String memberId);
    List<StatisticsEntity> findByMemberIdAndWeek(String memberId, String week);
}
