package com.tobias.clientservice.inner.domain;

import lombok.Data;

@Data
public class ResponseClientRequest {
    private int id;
    private int productId;
    private int clientId;
    private int amount;
}
