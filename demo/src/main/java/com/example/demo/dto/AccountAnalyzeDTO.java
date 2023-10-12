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
    private String totalAmount;

    public static AccountAnalyzeDTO toAccountAnalyzeDTO(AccountAnalyzeEntity accountAnalyzeEntity){
        AccountAnalyzeDTO accountAnalyzeDTO = new AccountAnalyzeDTO();
        accountAnalyzeDTO.setAnalyzeId(accountAnalyzeEntity.getAnalyzeId());
        accountAnalyzeDTO.setMemberId(accountAnalyzeEntity.getMemberId());
        accountAnalyzeDTO.setEntry(accountAnalyzeEntity.getEntry());
        accountAnalyzeDTO.setTotalAmount(accountAnalyzeEntity.getTotalAmount());
        return accountAnalyzeDTO;
    }
}
