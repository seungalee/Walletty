package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
@Transactional
public class AccountAnalyzeService {
    private final PaymentRepository paymentRepository;
    private final AccountAnalyzeRepository accountAnalyzeRepository;

    public String saveAmount(String memberId) { //entity객체는 service에서만
        List<PaymentEntity> paymentAll = paymentRepository.findByMemberId(memberId); // 해당 회원은 무조건 payment테이블에 결제내역이 1개 이상 있다고 가정 (나중에 예외처리 추가 해도 됨)

        for (PaymentEntity pay : paymentAll) {
            Optional<AccountAnalyzeEntity> analyzeEntry = accountAnalyzeRepository.findByEntryAndMemberId(pay.getEntry(), pay.getMemberId());
            if (analyzeEntry.isPresent()) {  // 분석 테이블에 이미 있는 항목이면 해당 항목에 값을 더해서 업데이트 하기
                // 1.
                //AccountAnalyzeEntity analyzeEntity = analyzeEntry.get();
                //AccountAnalyzeDTO dto = new AccountAnalyzeDTO(analyzeEntity.getAnalyzeId(), pay.getMemberId(), pay.getEntry(), pay.getAmount());
                //AccountAnalyzeEntity entity = AccountAnalyzeEntity.toAccountAnalyzeEntity(dto);
                //accountAnalyzeRepository.save(entity); // -> 원래 값이 update가 되는지, 새로 생성되는지 확인하기

                //2.
                //AccountAnalyzeEntity originEntity = analyzeEntry.get();
                //Integer updateAmount = originEntity.getTotalAmount() + pay.getAmount();
                //originEntity.setTotalAmount(updateAmount);
                //accountAnalyzeRepository.save(originEntity);

                //3.
                AccountAnalyzeEntity originEntity = analyzeEntry.get();
                Integer updateAmount = originEntity.getTotalAmount() + pay.getAmount();
                originEntity.setTotalAmount(updateAmount);

            } else { // 분석 테이블에 없는 항목이면 새로 항목 추가 후 값 넣기
                AccountAnalyzeDTO dto = new AccountAnalyzeDTO(pay.getMemberId(), pay.getEntry(), pay.getAmount());
                AccountAnalyzeEntity entity = AccountAnalyzeEntity.toAccountAnalyzeEntity(dto);
                accountAnalyzeRepository.save(entity);
            }
        }
        return "memberDTO"; //여기 나중에 수정
    }


    public List<AccountAnalyzeDTO> findByMemberId(String memberId) {
        List<AccountAnalyzeEntity> analyzeAll = accountAnalyzeRepository.findByMemberId(memberId);
        List<AccountAnalyzeDTO> aDTO = new ArrayList<>();
        for (AccountAnalyzeEntity entity : analyzeAll) {
            aDTO.add(AccountAnalyzeDTO.toAccountAnalyzeDTO(entity));
        }
        return aDTO;
    }
}
