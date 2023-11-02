package com.example.demo.entity;

import com.example.demo.dto.AccountAnalyzeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "account_analyze_table")
public class AccountAnalyzeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long analyzeId;

    @Column
    private String memberId;

    @Column
    private String entry;

    @Column
    private Integer totalAmount;

    @Column
    private String orderWeek;

    public static AccountAnalyzeEntity toAccountAnalyzeEntity(AccountAnalyzeDTO accountAnalyzeDTO){
        AccountAnalyzeEntity accountAnalyzeEntity = new AccountAnalyzeEntity();
        accountAnalyzeEntity.setAnalyzeId(accountAnalyzeDTO.getAnalyzeId());
        accountAnalyzeEntity.setMemberId(accountAnalyzeDTO.getMemberId());
        accountAnalyzeEntity.setEntry(accountAnalyzeDTO.getEntry());
        accountAnalyzeEntity.setTotalAmount(accountAnalyzeDTO.getTotalAmount());
        accountAnalyzeEntity.setOrderWeek(accountAnalyzeDTO.getOrderWeek());
        return accountAnalyzeEntity;
    }
}
