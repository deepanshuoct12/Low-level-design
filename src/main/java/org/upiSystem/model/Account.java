package org.upiSystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.upiSystem.exception.InsufficientBalanceException;

@Data
@AllArgsConstructor
public class Account {
    private String bankId;
    private String accountNumber;
    private Long   balance;

    public void debit(Long amount) throws InsufficientBalanceException {
     if (amount < balance) {
         throw new InsufficientBalanceException("Insufficient balance");
     }

     balance -= amount;
    }

    public void credit(Long amount) {
        balance += amount;
    }
}
