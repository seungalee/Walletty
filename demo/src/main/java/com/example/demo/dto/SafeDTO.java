package com.example.demo.dto;

import com.example.demo.entity.SafeEntity;
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

    public static SafeDTO toSafeDTO(SafeEntity safeEntity){
        SafeDTO safeDTO = new SafeDTO();
        safeDTO.setMissionId(safeEntity.getMissionId());
        safeDTO.setInSafe(safeEntity.isInSafe());
        safeDTO.setOutSafe(safeEntity.isOutSafe());

        return safeDTO;
    }
}
