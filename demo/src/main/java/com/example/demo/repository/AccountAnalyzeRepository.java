package com.example.demo.repository;

import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 첫번째 인자 : 어떤 Entity인지, 두번째 인자 : pk 어떤 타입인지
public interface AccountAnalyzeRepository extends JpaRepository<AccountAnalyzeEntity, String>
{
     //Optional<AccountAnalyzeEntity> findByEntry(String entry);
     Optional<AccountAnalyzeEntity> findByEntryAndMemberId(String entry, String memberId);
}
