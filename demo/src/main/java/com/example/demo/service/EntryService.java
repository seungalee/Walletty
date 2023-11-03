package com.example.demo.service;

import com.example.demo.dto.EntryDTO;
import com.example.demo.entity.EntryEntity;
import com.example.demo.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class EntryService {
    private final EntryRepository entryRepository; // 먼저 jpa, mysql dependency 추가

    public void save() {
        // repository의 save 메서드 호출
        ArrayList<EntryDTO> entryDTOList = new ArrayList<EntryDTO>();

        entryDTOList.add(new EntryDTO(1,"cafe","카페","음료 대신 물을 마시거나 음료를 사먹는 횟수를 줄여서"));
        entryDTOList.add(new EntryDTO(2,"deliver","배달 음식","직접 요리해서 먹거나 배달음식을 줄여서"));

        entryDTOList.add(new EntryDTO(3,"eatout","외식",""));
        entryDTOList.add(new EntryDTO(4,"beauty","미용",""));
        entryDTOList.add(new EntryDTO(5,"snack","간식",""));
        entryDTOList.add(new EntryDTO(6,"shopping","쇼핑",""));
        entryDTOList.add(new EntryDTO(7,"taxi","택시",""));

        // 값 넣기.
        for(EntryDTO entryDTO : entryDTOList){
            EntryEntity entryEntity = EntryEntity.toEntryEntity(entryDTO);
            entryRepository.save(entryEntity);
            //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
        }
    }
}
