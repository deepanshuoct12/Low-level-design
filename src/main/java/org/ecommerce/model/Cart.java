package org.ecommerce.model;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private String userId;
    private List<String> productIds;

    public Cart(String userId, List<String> productIds) {
        this.userId = userId;
        this.productIds = productIds;
    }
}
