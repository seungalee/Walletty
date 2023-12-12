package com.example.demo.controller;

import com.example.demo.service.AudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @ResponseBody
    @RequestMapping(value = "/cusvoice/audio", method = RequestMethod.POST)
    public LinkedHashMap getAudio(@RequestBody LinkedHashMap param) throws Exception{

        //위에 스트링으로 만들어준 객체를 답변을 위한 해쉬맵 객체에 넣어
        //프론트로 보내기 위해 적재
        return audioService.playAudio(param);
    }

}
