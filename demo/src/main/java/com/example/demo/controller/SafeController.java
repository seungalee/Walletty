package com.example.demo.controller;

import com.example.demo.service.MissionService;
import com.example.demo.service.SafeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SafeController {

    private final SafeService safeService;
    private final MissionService missionService;

    @RequestMapping("/missionAccept/{missionId}") //일단 금고 입금이 제대로 돌아가는지를 확인하기 위해 만듦.
    //실제는 프론트에서 수락 버튼을 누르면 missionId를 전달
    public void missionAccept(@PathVariable("missionId") int missionId){
        safeService.save(missionId); // 버튼 누르면 금고 테이블 입금 여부에 true
        missionService.saveAccept(missionId);//미션 테이블에 미션 수락 true
    }
}
