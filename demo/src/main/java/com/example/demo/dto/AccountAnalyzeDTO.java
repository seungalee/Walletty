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
    private String orderWeek;
    private Boolean okToUse; //미션이랑 피드백을 만들 때 사용을 했는지 여부

    public static AccountAnalyzeDTO toAccountAnalyzeDTO(AccountAnalyzeEntity accountAnalyzeEntity){
        AccountAnalyzeDTO accountAnalyzeDTO = new AccountAnalyzeDTO();
        accountAnalyzeDTO.setAnalyzeId(accountAnalyzeEntity.getAnalyzeId());
        accountAnalyzeDTO.setMemberId(accountAnalyzeEntity.getMemberId());
        accountAnalyzeDTO.setEntry(accountAnalyzeEntity.getEntry());
        accountAnalyzeDTO.setTotalAmount(accountAnalyzeEntity.getTotalAmount());
        accountAnalyzeDTO.setOrderWeek(accountAnalyzeEntity.getOrderWeek());
        accountAnalyzeDTO.setOkToUse(accountAnalyzeEntity.getOkToUse());
        return accountAnalyzeDTO;
    }

    public AccountAnalyzeDTO(String memberId, String entry, Integer totalAmount, String orderWeek, Boolean okToUse) {
        // 전역 변수에 인풋으로 들어온 변수값 매핑
        this.memberId = memberId;
        this.entry = entry;
        this.totalAmount = totalAmount;
        this.orderWeek = orderWeek;
        this.okToUse = okToUse;
    }
    public AccountAnalyzeDTO(long analyzeId, String memberId, String entry, Integer totalAmount) {
        // 전역 변수에 인풋으로 들어온 변수값 매핑
        this.analyzeId = analyzeId;
        this.memberId = memberId;
        this.entry = entry;
        this.totalAmount = totalAmount;
    }
}
