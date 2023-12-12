package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "ranking_table")
public class RankingEntity {
    @Id
    @Column
    private String memberId;
    @Column
    private Integer level;
    @Column
    private String position;
    @Column
    private Integer totalSavingMoney;
    @Column
    private Integer ranks;
    @Column
    private Boolean isFriend;
}
