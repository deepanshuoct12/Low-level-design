package org.upiSystem.model;

import lombok.Builder;
import lombok.Data;
import org.upiSystem.constants.UserStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    UserStatus status;
    private List<Account> accounts;
    private List<String> transactionIds;
    private Account primaryBankAccount;


    public List<Account> getAccounts() {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }

        return accounts;
    }

    public List<String> getTransactionIds() {
        if (transactionIds == null) {
            transactionIds = new ArrayList<>();
        }

        return transactionIds;
    }
}
