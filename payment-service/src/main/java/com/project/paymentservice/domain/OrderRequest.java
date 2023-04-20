package com.project.paymentservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class OrderRequest {

    private String customerName;
    private String email;
    private String phoneNumber;
    private BigInteger amount;
}
