package com.example.demo.repository;

import com.example.demo.entity.SurveyLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyLimitRepository extends JpaRepository<SurveyLimitEntity, String> {
}
