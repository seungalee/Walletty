package com.example.demo.repository;

import com.example.demo.entity.RankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity, String> {
    List<RankingEntity> findByIsFriend(Boolean isFriend);
    RankingEntity findByRanks(Integer ranks);
}
