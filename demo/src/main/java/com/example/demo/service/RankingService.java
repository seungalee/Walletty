package com.example.demo.service;

import com.example.demo.dto.RankingDTO;
import com.example.demo.entity.RankingEntity;
import com.example.demo.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    public List<RankingDTO> findByIsFriend() {
        List<RankingEntity> rankingEntityList = rankingRepository.findByIsFriend(true);
        List<RankingDTO> rankingDTOList = new ArrayList<>();
        for(RankingEntity ent : rankingEntityList){
            rankingDTOList.add(RankingDTO.toRankingDTO(ent));
        }
        return rankingDTOList;
    }

    public List<RankingDTO> findByRank() {

        List<RankingDTO> rankingDTOList = new ArrayList<>();

        RankingEntity rankingEntity1 = rankingRepository.findByRank(1);
        rankingDTOList.add(RankingDTO.toRankingDTO(rankingEntity1));
        RankingEntity rankingEntity2 = rankingRepository.findByRank(2);
        rankingDTOList.add(RankingDTO.toRankingDTO(rankingEntity2));
        RankingEntity rankingEntity3 = rankingRepository.findByRank(3);
        rankingDTOList.add(RankingDTO.toRankingDTO(rankingEntity3));

        return rankingDTOList;
    }

}
