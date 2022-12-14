 package com.tobias.clientservice.outer.dto;

import lombok.Data;

@Data
public class ExchangeRequestDto {
    private int id;
    private int productId;
    private int clientId;
    private int amount;
    private String size;
}
