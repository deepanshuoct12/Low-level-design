package org.ecommerce.model;


import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private Integer price;

    public Product(String name, String description, Integer price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
