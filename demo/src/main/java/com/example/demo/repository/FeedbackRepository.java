package com.example.demo.repository;

import com.example.demo.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findByMemberId(String memberId);
    Optional<FeedbackEntity> findByMemberIdAndOkToSend(String memberId, String okToSend);
}
