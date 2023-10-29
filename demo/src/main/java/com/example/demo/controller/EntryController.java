package com.example.demo.controller;

import com.example.demo.dto.EntryDTO;
import com.example.demo.service.EntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class EntryController {
    private final EntryService entryService;

    @RequestMapping("/saveEntry")
    public void saveFeedbackComment(){
        ArrayList<EntryDTO> entryDTOList = new ArrayList<EntryDTO>();

        entryDTOList.add(new EntryDTO(1,"cafe","카페","음료 대신 물을 마시거나 음료를 사먹는 횟수를 줄여서"));
        entryDTOList.add(new EntryDTO(2,"eatout","배달 음식","직접 요리해서 먹거나 배달음식을 줄여서")); // 외식인데 delivery를 아직 결제내역에 안넣어놔서 임시로 배달 음식이라고 적음

        // 값 넣기.
        for(EntryDTO dtos : entryDTOList){
            entryService.save(dtos);
        }
    }
}
