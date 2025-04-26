package org.upiSystem.dao;

import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDao {
    private static List<Transaction> transactions = new ArrayList<>();
   private HashMap<String, List<Transaction>> userFromIndex = new HashMap<>();
    private HashMap<String, List<Transaction>> userToIndex = new HashMap<>();

    public void add(Transaction transaction) throws InvalidInputException {
        if (transaction == null) {
            throw new InvalidInputException("Invalid input");
        }

        update(transaction);
        transactions.add(transaction);
    }

    public List<Transaction> getUserFrom(String id) {
        List<Transaction> transactionList = userFromIndex.get(id);
        //transactions.stream().filter(transaction -> transaction.getUserFrom().equals(id)).collect(Collectors.toList());
        return transactionList;
    }

    public List<Transaction> getUserTo(String id) {
        List<Transaction> transactionList = userToIndex.get(id);
        //transactions.stream().filter(transaction -> transaction.getUserTo().equals(id)).collect(Collectors.toList());
        return transactionList;
    }

    public List<Transaction> getForUserAndAccount(String userId, String accountId) {
        List<Transaction> transactionList = transactions.stream().filter(transaction -> transaction.getUserFrom().equals(userId) && transaction.getFromAccountId().equals(accountId)).collect(Collectors.toList());
        return transactionList;
    }

    public void update(Transaction transaction) {
        transactions.removeIf(transactionObj -> transactionObj.getId().equals(transaction.getId()));
        transactions.add(transaction);
        userToIndex.get(transaction.getUserTo()).removeIf(transactionObj -> transactionObj.getId().equals(transaction.getId()));
        userFromIndex.get(transaction.getUserFrom()).removeIf(transactionObj -> transactionObj.getId().equals(transaction.getId()));

        updateIndexes(transaction);

    }

    private void updateIndexes(Transaction transaction) {
        userFromIndex.getOrDefault(transaction.getUserFrom(), new ArrayList<>()).add(transaction);
        userToIndex.getOrDefault(transaction.getUserTo(), new ArrayList<>()).add(transaction);
    }
}
