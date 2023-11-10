package com.example.demo.controller;

import com.example.demo.dto.AccountAnalyzeDTO;
import com.example.demo.dto.MissionDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.service.AccountAnalyzeService;
import com.example.demo.service.MissionService;
import com.example.demo.service.ProfileService;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final ProfileService profileService;

    // 이번주 사용 금액과 저번주 미션 금액을 비교해서 미션 성공 실패를 알림.
    @RequestMapping("/isMissionSuccess/{missionId}")
    public void isSuccess(@PathVariable("missionId") int missionId){

        //missionService.isMissionSuccess(missionId);

        // 미션 성공 or 실패 한 경우 profile update
        profileService.updateSuccess(missionId); // 데모를 위해 무조건 success만 했다고 가정
    }




}
