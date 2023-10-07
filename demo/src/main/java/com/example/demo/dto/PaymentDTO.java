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

    private String mid;

    //private String transactionKey;
    private String paymentKey;
    private String orderId;
    private String method;

    //private String customerKey;
    //private boolean useEscrow;
    //private String receiptUrl;

    private String status;
    private String transactionAt;
    //private String currency;

    private int amount;

    //항목, 거래시간
    private String entry;
    private String orderTime;



}


/*
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

    private String mid;

    //private String lastTransactionKey;
    private String paymentKey;
    private String orderId;
    private String orderName;

    //private int taxExemptionAmount;

    private String status;
    private String requestedAt;
    private String approvedAt;

    //private boolean useEscrow;
    //private boolean cultureExpense;

    //private String card;
    //private String virtualAccount;
    //private String transfer;
    //private String mobilePhone;
    //private String giftCertificate;
    //private String cashReceipt;
    //private String cashReceipts;
    //private String discount;
    //private String cancels;
    //private String secret;
    private String type;
    //private String easyPay;
    //private String country;
    //private String failure;
    //private boolean isPartialCancelable;
    //private String receipt;
    //private String checkout;
    //private String currency;

    private int totalAmount;
    //private int balanceAmount;
    //private int suppliedAmount;
    //private int vat;
    //private int taxFreeAmount;

    private String method;
    //private String version;

    //항목, 거래시간
    private String entry;
    private String orderTime;



}
*/