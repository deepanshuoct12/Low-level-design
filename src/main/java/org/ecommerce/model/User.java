package org.ecommerce.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private String id;
    private String name;
    private String emailId;
    private String role;

    public User(String name, String emailId, String role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.emailId = emailId;
        this.role = role;
    }
}
