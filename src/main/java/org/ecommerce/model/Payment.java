package org.ecommerce.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Payment {
    private String id;
    private String userId;
    private String orderId;
    private String status;

    public Payment(String userId, String orderId, String status) {
        this.id =  UUID.randomUUID().toString();
        this.userId = userId;
        this.orderId = orderId;
        this.status = status;
    }
}
