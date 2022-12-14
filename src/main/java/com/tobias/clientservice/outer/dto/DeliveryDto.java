package com.tobias.clientservice.outer.dto;

import lombok.Data;

@Data
public class DeliveryDto {
    private int id;
    private int productId;
    private int clientId;
    private int amount;
}
