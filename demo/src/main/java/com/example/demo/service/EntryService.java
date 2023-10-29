package com.example.demo.service;

import com.example.demo.dto.EntryDTO;
import com.example.demo.entity.EntryEntity;
import com.example.demo.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class EntryService {
    private final EntryRepository entryRepository; // 먼저 jpa, mysql dependency 추가

    public void save(EntryDTO entryDTO) {
        // repository의 save 메서드 호출
        EntryEntity entryEntity = EntryEntity.toEntryEntity(entryDTO);
        entryRepository.save(entryEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }
}
