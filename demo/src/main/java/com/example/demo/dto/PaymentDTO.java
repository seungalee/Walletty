package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class PaymentDTO {

    private Long id;

    private String memberId;

    // private String mid; // "mId": "tvivarepublica", // 어차피 다 똑같으니

    // private String paymentKey;

    private String orderId; //"orderId": "pay-cafe-1-0901"

    private String status;

    private String transactionAt; // "transactionAt": "2023-10-06T17:53:38+09:00"

    private int amount;

    private String entry; //항목

    private String orderTime; //거래시간


}
