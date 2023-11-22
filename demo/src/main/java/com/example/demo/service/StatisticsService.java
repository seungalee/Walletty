package com.example.demo.service;

import com.example.demo.dto.StatisticsDTO;
import com.example.demo.entity.StatisticsEntity;
import com.example.demo.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsDTO findByMemberId(String memberId){
        Optional<StatisticsEntity> statisticsEntity = statisticsRepository.findByMemberId(memberId);
        StatisticsDTO statisticsDTO = StatisticsDTO.toStatisticsDTO(statisticsEntity.get());
        return statisticsDTO;
    }
}
