package com.example.demo.controller;

import com.example.demo.dto.RankingDTO;
import com.example.demo.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;


    @RequestMapping("/rankingTop3") // 서비스 전체에서 ranking 탑3 프론트로 보내줌
    public List<RankingDTO> sendRankingTop3(){
        List<RankingDTO> rankingDTOs = rankingService.findByRank();
        return rankingDTOs;
    }

    @RequestMapping("/rankingFriend") // 친구들 ranking 프론트로 보내줌
    public List<RankingDTO> sendRankingFriend(){
        List<RankingDTO> rankingDTOs = rankingService.findByIsFriend();
        return rankingDTOs;
    }


}
