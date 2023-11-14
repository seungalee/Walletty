package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EntryDTO {
    public Integer id;
    public String entry;
    public String entryKorean;
    public String solution;

    public EntryDTO(Integer id, String entry, String entryKorean, String solution) {
        // 전역 변수에 인풋으로 들어온 변수값 매핑
        this.id = id;
        this.entry = entry;
        this.entryKorean = entryKorean;
        this.solution = solution;
    }
}

