package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SafeDTO {
    private int missionId;
    private boolean inSafe;
    private boolean outSafe;
}
