package com.example.demo.repository;

import com.example.demo.entity.FeedbackCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 첫번째 인자 : 어떤 Entity인지, 두번째 인자 : pk 어떤 타입인지
public interface FeedbackCommentRepository extends JpaRepository<FeedbackCommentEntity, Integer>
{
    // 이메일로 회원 정보 조회( select * from member_table where member_email=?)
    Optional<FeedbackCommentEntity> findById(Integer id);
}