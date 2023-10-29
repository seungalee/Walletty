package com.example.demo.entity;

import com.example.demo.dto.EntryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "entry_table") //database에 해당 이름의 테이블 생성
public class EntryEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column(unique = true)
    public String entry;

    @Column
    public String entryKorean;

    @Column
    public String solution;

    public static EntryEntity toEntryEntity(EntryDTO entryDTO){
        EntryEntity entryEntity = new EntryEntity();
        entryEntity.setId(entryDTO.getId());
        entryEntity.setEntry(entryDTO.getEntry());
        entryEntity.setEntryKorean(entryDTO.getEntryKorean());
        entryEntity.setSolution(entryDTO.getSolution());
        return entryEntity;
    }
}



