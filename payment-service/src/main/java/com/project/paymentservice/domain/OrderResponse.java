package com.project.paymentservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponse {

    private String secretKey;
    private String razorpayOrderId;
    private String applicationFee;
    private String secretId;
    private String pgName;
}
