package com.tobias.clientservice.outer.dto;

import lombok.Data;

@Data
public class ClientRequestDto {
    private int id;
    private int itemId;
    private int clientId;
    private int amount;
}
