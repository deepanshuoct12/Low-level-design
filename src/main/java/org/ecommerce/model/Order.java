package org.ecommerce.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private String id;
    private List <String> productIds;
    private String userId;
    private String status;
    private Integer value;

    public Order(List<String> productIds, String userId, String status) {
        this.id =  UUID.randomUUID().toString();
        this.productIds = productIds;
        this.userId = userId;
        this.status = status;
    }
}
