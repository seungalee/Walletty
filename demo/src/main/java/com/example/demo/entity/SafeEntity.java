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
@Table(name = "safe_table")
public class SafeEntity {

    @Id
    @Column
    private int missionId;  // missionId(unique)랑 매칭되는 금고의 primary key
    @Column
    private boolean inSafe;
    @Column
    private boolean outSafe;
}
