package com.example.demo.service;

import com.example.demo.dto.AccountAnalyzeDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.entity.PaymentEntity;
import com.example.demo.repository.AccountAnalyzeRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class AccountAnalyzeService {

    // private final MemberRepository memberRepository; // 먼저 jpa, mysql dependency 추가

    private final PaymentRepository paymentRepository;
    private final AccountAnalyzeRepository accountAnalyzeRepository;

    public MemberDTO saveAmount(MemberDTO memberDTO){ //entity객체는 service에서만
        List<PaymentEntity> paymentAll = paymentRepository.findAll();

        for(PaymentEntity pay : paymentAll){
            Optional<AccountAnalyzeEntity> analyzeEntry = accountAnalyzeRepository.findByEntryAndMemberId(pay.getEntry(),pay.getMemberId());
            if(analyzeEntry.isPresent()){  // 분석 테이블에 이미 있는 항목이면 해당 항목에 값을 더해서 업데이트 하기
                AccountAnalyzeEntity analyzeEntity = analyzeEntry.get();
                AccountAnalyzeDTO dto = new AccountAnalyzeDTO(analyzeEntity.getAnalyzeId(), pay.getMemberId(), pay.getEntry(), pay.getAmount());
                AccountAnalyzeEntity entity = AccountAnalyzeEntity.toAccountAnalyzeEntity(dto);
                accountAnalyzeRepository.save(entity); // -> 원래 값이 update가 되는지, 새로 생성되는지 확인하기
            } else { // 분석 테이블에 없는 항목이면 새로 항목 추가 후 값 넣기
                AccountAnalyzeDTO dto = new AccountAnalyzeDTO(pay.getMemberId(), pay.getEntry(), pay.getAmount());
                AccountAnalyzeEntity entity = AccountAnalyzeEntity.toAccountAnalyzeEntity(dto);
                accountAnalyzeRepository.save(entity);
            }
        }
        return null; //여기 나중에 수정
    }
}
