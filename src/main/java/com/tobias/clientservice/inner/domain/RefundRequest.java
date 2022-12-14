package com.tobias.clientservice.inner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class RefundRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int clientId;
    private int amount;
    private String reason;

    public static RefundRequest createRefundRequest(RequestRefundRequest requestDelivery){
        RefundRequest delivery = new RefundRequest();
        delivery.setProductId(requestDelivery.getProductId());
        delivery.setClientId(requestDelivery.getClientId());
        delivery.setAmount(requestDelivery.getAmount());
        delivery.setReason(requestDelivery.getReason());
        return delivery;
    }
}
