package com.example.demo.repository;

import com.example.demo.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 첫번째 인자 : 어떤 Entity인지, 두번째 인자 : pk 어떤 타입인지
public interface PaymentRepository extends JpaRepository<PaymentEntity, String>
{
    Optional<PaymentEntity> findByOrderId(String orderId);
}
//PaymentRepository.interface
