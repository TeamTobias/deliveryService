package com.tobias.clientservice.inner.domain;

import lombok.Data;

@Data
public class RequestRefundRequest {
    private int id;
    private int productId;
    private int clientId;
    private int amount;
    private String reason;
}
