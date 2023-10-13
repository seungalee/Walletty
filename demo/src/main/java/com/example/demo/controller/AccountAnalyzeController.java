package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountAnalyzeController {
    private final AccountAnalyzeService accountAnalyzeService;

    @RequestMapping("/saveTotalAmount")
    public MemberDTO saveTotalAmount(@RequestBody MemberDTO memberDTO){ // 분석테이블에 총 금액을 저장하길 원하는 회원 정보 (id값만 쓰임)
        accountAnalyzeService.saveAmount(memberDTO);
        return memberDTO;
    }

}
