package org.upiSystem.service;

import org.upiSystem.constants.UserRole;
import org.upiSystem.dao.TransactionDao;
import org.upiSystem.model.Transaction;

import java.util.List;

public class RecieverTransactionStratergy implements ITransactionStratergyService{
    private TransactionDao transactionDao = new TransactionDao();

    @Override
    public boolean isApplicable(UserRole userRole) {
        return userRole.equals(UserRole.RECIEVER);
    }

    @Override
    public List<Transaction> getTransactions(String id) {
        return transactionDao.getUserTo(id);
    }
}
