package com.example.demo.entity;

import com.example.demo.dto.PaymentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "payment_table") //database에 해당 이름의 테이블 생성

public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String memberId;

    //private String mid; // "mId": "tvivarepublica",

    //@Column
    //private String paymentKey;

    @Column
    private String orderId; //"orderId": "pay-cafe-1-0901"

    @Column
    private String status;

    @Column
    private String transactionAt; // "transactionAt": "2023-10-06T17:53:38+09:00"

    @Column
    private int amount;

    @Column
    private String entry; //항목

    @Column
    private String orderDate; //거래날짜

    public static PaymentEntity toPaymentEntity(PaymentDTO paymentDTO){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(paymentDTO.getId());
        paymentEntity.setMemberId(paymentDTO.getMemberId());
        paymentEntity.setOrderId(paymentDTO.getOrderId());
        paymentEntity.setStatus(paymentDTO.getStatus());
        paymentEntity.setTransactionAt(paymentDTO.getTransactionAt());
        paymentEntity.setAmount(paymentDTO.getAmount());
        paymentEntity.setEntry(paymentDTO.getEntry());
        paymentEntity.setOrderDate(paymentDTO.getOrderDate());

        return paymentEntity;
    }
}
