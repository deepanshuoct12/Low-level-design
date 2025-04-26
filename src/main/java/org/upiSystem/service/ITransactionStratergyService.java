package org.upiSystem.service;

import org.upiSystem.constants.UserRole;
import org.upiSystem.model.Transaction;

import java.util.List;

public interface ITransactionStratergyService {
    boolean isApplicable(UserRole userRole);

    List<Transaction> getTransactions(String id);
}
