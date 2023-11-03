package com.example.demo.repository;

import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // 첫번째 인자 : 어떤 Entity인지, 두번째 인자 : pk 어떤 타입인지
public interface AccountAnalyzeRepository extends JpaRepository<AccountAnalyzeEntity, Long>
{
     //Optional<AccountAnalyzeEntity> findByEntry(String entry);
     List<AccountAnalyzeEntity> findByMemberId(String memberId);

     Optional<AccountAnalyzeEntity> findByEntryAndMemberId(String entry, String memberId);

     Optional<AccountAnalyzeEntity> findByEntryAndMemberIdAndOrderWeek(String entry, String memberId, String orderWeek);
     Optional<AccountAnalyzeEntity> findByEntryAndMemberIdAndOkToUse(String entry, String memberId, Boolean okToUse);
     List<AccountAnalyzeEntity> findByMemberIdAndOkToUse(String memberId, Boolean okToUse);

     List<AccountAnalyzeEntity> findByOkToUse(Boolean okToUse);

     AccountAnalyzeEntity findByEntryAndMemberIdAndOkToUseAndOrderWeek(String entry, String memberId, boolean b, String orderWeek);

     List<AccountAnalyzeEntity> findByMemberIdAndOrderWeek(String memberId, String orderWeek);
}
