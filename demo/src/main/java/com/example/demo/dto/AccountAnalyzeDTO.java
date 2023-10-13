package com.example.demo.dto;

import com.example.demo.entity.AccountAnalyzeEntity;
import com.example.demo.entity.FeedbackEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountAnalyzeDTO {
    private long analyzeId;
    private String memberId;
    private String entry;
    private Integer totalAmount;

    public static AccountAnalyzeDTO toAccountAnalyzeDTO(AccountAnalyzeEntity accountAnalyzeEntity){
        AccountAnalyzeDTO accountAnalyzeDTO = new AccountAnalyzeDTO();
        accountAnalyzeDTO.setAnalyzeId(accountAnalyzeEntity.getAnalyzeId());
        accountAnalyzeDTO.setMemberId(accountAnalyzeEntity.getMemberId());
        accountAnalyzeDTO.setEntry(accountAnalyzeEntity.getEntry());
        accountAnalyzeDTO.setTotalAmount(accountAnalyzeEntity.getTotalAmount());
        return accountAnalyzeDTO;
    }

    public AccountAnalyzeDTO(String memberId, String entry, Integer totalAmount) {
        // 전역 변수에 인풋으로 들어온 변수값 매핑
        this.memberId = memberId;
        this.entry = entry;
        this.totalAmount = totalAmount;
    }
    public AccountAnalyzeDTO(long analyzeId, String memberId, String entry, Integer totalAmount) {
        // 전역 변수에 인풋으로 들어온 변수값 매핑
        this.analyzeId = analyzeId;
        this.memberId = memberId;
        this.entry = entry;
        this.totalAmount = totalAmount;
    }
}
