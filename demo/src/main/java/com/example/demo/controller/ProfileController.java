package com.example.demo.controller;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/profile")
    public ProfileDTO sendProfile(@RequestBody String memberId){ //프론트가 요청했을 때 프로필 DTO Post
        ProfileDTO profileDTO = profileService.findByMemberId(memberId);
        return profileDTO;
    }
}
