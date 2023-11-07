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
        List<PaymentEntity> paymentAll = paymentRepository.findByMemberId(memberId);
        // 해당 회원은 무조건 payment테이블에 결제내역이 1개 이상 있다고 가정 (나중에 예외처리 추가 해도 됨)

        for (PaymentEntity pay : paymentAll) {

            int paydate = Integer.parseInt(pay.getOrderDate());
            if(paydate>=1101 && paydate<=1107) {
                paydate= 1107;
            } else if (paydate>=1108 && paydate<=1114) {
                paydate = 1114;
            }

            Optional<AccountAnalyzeEntity> analyzeEntry =
                    accountAnalyzeRepository.findByEntryAndMemberIdAndOrderWeek(pay.getEntry(), pay.getMemberId(), Integer.toString(paydate));

            if (analyzeEntry.isPresent()){  // 분석 테이블의 해당 주차에 이미 있는 항목이면 해당 항목에 값을 더해서 업데이트 하기

                AccountAnalyzeEntity originEntity = analyzeEntry.get();
                Integer updateAmount = originEntity.getTotalAmount() + pay.getAmount();
                originEntity.setTotalAmount(updateAmount);


            } else { // 분석 테이블의 해당 주차에 없는 항목이면 새로 항목 추가 후 값 넣기

                AccountAnalyzeDTO dto = new AccountAnalyzeDTO(pay.getMemberId(), pay.getEntry(), pay.getAmount(), Integer.toString(paydate), false);
                AccountAnalyzeEntity entity = AccountAnalyzeEntity.toAccountAnalyzeEntity(dto);
                accountAnalyzeRepository.save(entity);
            }
        }
        return "memberDTO"; //여기 나중에 수정
    }

    public String findThisWeek(String selectedMemberId){
        // List<AccountAnalyzeEntity> thisWeek = accountAnalyzeRepository.findByMemberIdAndOkToUse(selectedMemberId, false); //이번주차 결제내역 분석
        List<AccountAnalyzeEntity> thisWeek = accountAnalyzeRepository.findByMemberIdAndOkToUse(selectedMemberId, false); //이번주차 결제내역 분석
        AccountAnalyzeEntity thisDate = thisWeek.get(0); // 첫 번째 객체 (어차피 orderWeek 다 같으니까)
        String endDateOfAnalyze = thisDate.getOrderWeek(); // 이번주차 분석의 결제내역 마지막 날
        Integer startDateOfMission = Integer.parseInt(endDateOfAnalyze) + 1; // 의 다음날이 다음주차 미션 시작 날
        String startDate = String.valueOf(startDateOfMission);
        return startDate;
    }

    public String findLastWeek(String selectedMemberId){
        List<AccountAnalyzeEntity> thisWeek = accountAnalyzeRepository.findByMemberIdAndOkToUse(selectedMemberId, true); //직전주차 결제내역 분석 //지금은 이렇게 해두고 나중에 바꾸기
        AccountAnalyzeEntity thisDate = thisWeek.get(0); // 첫 번째 객체 (어차피 orderWeek 다 같으니까)
        String endDateOfAnalyze = thisDate.getOrderWeek(); // 이번주차 분석의 결제내역 마지막 날
        Integer startDateOfMission = Integer.parseInt(endDateOfAnalyze) + 1; // 의 다음날이 다음주차 미션 시작 날
        String startDate = String.valueOf(startDateOfMission);
        return startDate;
    }

    public void changeOkToUseWithTrue(String memberId){ // 피드백, 미션 다 만들고 난 뒤 이번주차 결제내역 분석 항목들 모두 okToUse바꿔주기
        List<AccountAnalyzeEntity> thisWeekEntity = accountAnalyzeRepository.findByMemberIdAndOkToUse(memberId,false);
        for (AccountAnalyzeEntity entity : thisWeekEntity) {
            entity.setOkToUse(true);
        }
    }
    public List<AccountAnalyzeDTO> findByMemberIdAndOkToUse(String memberId, Boolean okToUse) {
        List<AccountAnalyzeEntity> analyzeAll = accountAnalyzeRepository.findByMemberIdAndOkToUse(memberId,okToUse);
        List<AccountAnalyzeDTO> aDTO = new ArrayList<>();
        for (AccountAnalyzeEntity entity : analyzeAll) {
            aDTO.add(AccountAnalyzeDTO.toAccountAnalyzeDTO(entity));
        }
        return aDTO;
    }

    public List<AccountAnalyzeDTO> findByOkToUse(Boolean okToUse) {
        List<AccountAnalyzeEntity> analyzeAll = accountAnalyzeRepository.findByOkToUse(okToUse);
        List<AccountAnalyzeDTO> aDTO = new ArrayList<>();
        for (AccountAnalyzeEntity entity : analyzeAll) {
            aDTO.add(AccountAnalyzeDTO.toAccountAnalyzeDTO(entity));
        }
        return aDTO;
    }

    public List<AccountAnalyzeDTO> findByMemberIdAndOrderWeek(String memberId, String orderWeek) {
        List<AccountAnalyzeEntity> analyzeAll = accountAnalyzeRepository.findByMemberIdAndOrderWeek(memberId,orderWeek);
        List<AccountAnalyzeDTO> aDTO = new ArrayList<>();
        for (AccountAnalyzeEntity entity : analyzeAll) {
            aDTO.add(AccountAnalyzeDTO.toAccountAnalyzeDTO(entity));
        }
        return aDTO;
    }
}
