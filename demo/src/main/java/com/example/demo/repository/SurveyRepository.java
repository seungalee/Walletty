package com.example.demo.repository;

import com.example.demo.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, String>
{
    Optional<SurveyEntity> findBySurveyId(String surveyId);

    SurveyEntity findTopBy();
}
