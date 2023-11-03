package com.example.demo.service;

import com.example.demo.ChatGptConfig;
import com.example.demo.ChatGptMessage;
import com.example.demo.dto.AccountAnalyzeDTO;
import com.example.demo.dto.ChatGptRequest;
import com.example.demo.dto.ChatGptResponse;
import com.example.demo.dto.QuestionRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatGptService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final AccountAnalyzeRepository accountAnalyzeRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final EntryRepository entryRepository;

    //api key를 application.yml에 넣어두었습니다.
    @Value("${api-key.chat-gpt}")
    private String apiKey;

    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatGptRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        httpHeaders.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return new HttpEntity<>(chatGptRequest, httpHeaders);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequestHttpEntity){

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        //답변이 길어질 경우 TimeOut Error가 발생하니 1분정도 설정해줍니다.
        requestFactory.setReadTimeout(60 * 1000);   //  1min = 60 sec * 1,000ms
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.CHAT_URL,
                chatGptRequestHttpEntity,
                ChatGptResponse.class);

        return responseEntity.getBody();
    }
    public ChatGptResponse askQuestion(String selectedMemberId, String startDate){
        Optional<MemberEntity> memberEntity = memberRepository.findByMemberId(selectedMemberId); // 사용자 이름
        Optional<MissionEntity> missionEntity  = missionRepository.findByMemberIdAndStartDate(selectedMemberId,startDate); //다음주차 미션 항목을 가져오기 위함.
        Optional<EntryEntity> entryEntity = entryRepository.findByEntry(missionEntity.get().getMissionEntry()); // 영어 ->한글 변환을 위함
        Optional<AccountAnalyzeEntity> accountAnalyzeEntity = accountAnalyzeRepository.findByEntryAndMemberIdAndOkToUse(
                missionEntity.get().getMissionEntry(),selectedMemberId,false); // 해당 사용자의 이번주 피드백 항목에 대한 totalAmount를 얻기 위함.

        String finalQuestion = "내 이름은 " + memberEntity.get().getMemberName() + "이고, 내가 "
                + entryEntity.get().getEntryKorean() + "에 " + accountAnalyzeEntity.get().getTotalAmount() + "원을 썼는데 나에게 "
                + entryEntity.get().getSolution() + " 돈을 절약하라고 엄마 말투로 짧게 잔소리를 해줘.";

        System.out.println(finalQuestion);

        List<ChatGptMessage> messages = new ArrayList<>();
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.USER)
                .content("내 이름은 민지고, 매달 헬스장 회비를 내야돼. 돈을 절약하라고 엄마처럼 잔소리를 해줘")
                .build());
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ASSISTANT)
                .content("돈이 그렇게 넘쳐나니? 헬스장 회비를 내기 전에 가까운 공원에서 운동해봐. 무료로 운동할 수 있는 앱이나 온라인 운동 프로그램도 활용해보면 좋아. 그리고 헬스장 회원 자격을 공유하거나, 기간 한정 할인을 활용하는 등 할인 방법도 찾아봐.")
                .build());
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.SYSTEM)
                .content("assistant는 잔소리하는 엄마야")
                .build());
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ROLE)
//                .content(questionRequest.getQuestion())
                .content(finalQuestion)
                .build());
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                ChatGptConfig.CHAT_MODEL,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.STREAM,
                                messages
                                //ChatGptConfig.TOP_P
                        )
                )
        );
    }

    public ChatGptResponse askQuestionM(String selectedMemberId, String startDate){
        Optional<MissionEntity> missionEntity = missionRepository.findByMemberIdAndStartDate(selectedMemberId, startDate);
        Optional<EntryEntity> entryEntity = entryRepository.findByEntry(missionEntity.get().getMissionEntry());


        String finalQuestion = "이번 주는 " + entryEntity.get().getEntryKorean() + "비를 "
                + missionEntity.get().getMissionMoney()
                + "원 이하로 돈을 쓰라고 엄마 말투로 한줄로 잔소리를 해줘.";

        System.out.println(finalQuestion);

        List<ChatGptMessage> messages = new ArrayList<>();
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.USER)
                .content("이번 주는 교통비를 1000원 이하로 돈을 쓰라고 엄마 말투로 한줄로 잔소리를 해줘.") // 나중에 보고 수정
                .build());
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ASSISTANT)
                .content("이번 주에는 교통비를 1000원 이하로 쓰도록 해!")
                .build());
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.SYSTEM)
                .content("assistant는 잔소리하는 엄마야")
                .build());
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ROLE)
//                .content(questionRequest.getQuestion())
                .content(finalQuestion)
                .build());
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                ChatGptConfig.CHAT_MODEL,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.STREAM,
                                messages
                                //ChatGptConfig.TOP_P
                        )
                )
        );
    }
}