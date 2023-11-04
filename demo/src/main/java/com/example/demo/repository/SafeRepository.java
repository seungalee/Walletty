package com.example.demo.repository;

import com.example.demo.entity.SafeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeRepository  extends JpaRepository<SafeEntity, Integer> {

}
