package com.example.demo.controller;

import com.example.demo.dto.StatisticsDTO;
import com.example.demo.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;


    @RequestMapping("/statisticsDTO")
    public StatisticsDTO sendStatisticsDTO(@RequestBody StatisticsDTO statistics){
        StatisticsDTO statisticsDTO = statisticsService.findByMemberId(statistics.getMemberId());
        return statisticsDTO;
    }

//    @RequestMapping("/paymentList")

//    @RequestMapping("/accountAnalyzeList")

}
