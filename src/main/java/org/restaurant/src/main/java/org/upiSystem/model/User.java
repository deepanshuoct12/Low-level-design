package org.upiSystem.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String phoneNumber;

    public User(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
