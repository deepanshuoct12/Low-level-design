package org.upiSystem.model;

import lombok.Data;
import org.upiSystem.constants.BankStatus;

@Data
public class Bank {
    private String id;
    private String name;
    BankStatus status;

    public Bank(String id, String name, BankStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
