package org.upiSystem.service;

import org.upiSystem.constants.BankStatus;
import org.upiSystem.constants.TransactionStatus;
import org.upiSystem.constants.UserRole;
import org.upiSystem.dao.TransactionDao;
import org.upiSystem.exception.BankNotFoundException;
import org.upiSystem.exception.BankServerDownException;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.PendingStatusException;
import org.upiSystem.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private TransactionDao transactionDao = new TransactionDao();
    private BankService bankService = new BankService();
    private List<ITransactionStratergyService> transactionStratergyServices;

    public TransactionService() {
        transactionStratergyServices = new ArrayList<>();
        transactionStratergyServices.add(new SenderTransactionStratergy());
        transactionStratergyServices.add(new RecieverTransactionStratergy());
    }

    /**
     * synchronized for handling concurrent transaction
     */
    public synchronized Transaction addTransaction(TransferInfo transferInfo, User userFrom, User userTo, Long amount) throws InvalidInputException, BankServerDownException, PendingStatusException, BankNotFoundException {
        String accountFromNumber = transferInfo.getAccountFromNumber();
        String accountToNumber = transferInfo.getAccountFromNumber();

        Account accountFrom = userFrom.getAccounts().stream().filter(account -> account.getAccountNumber().equals(accountFromNumber)).findFirst().orElse(null);
        Account accountTo = userFrom.getAccounts().stream().filter(account -> account.getAccountNumber().equals(accountToNumber)).findFirst().orElse(null);

        if (accountFrom == null || accountTo == null) {
            throw new InvalidInputException("Account number not linked with bank");
        }

        Bank bankFrom = bankService.getBank(accountFrom.getBankId());
        Bank bankTo = bankService.getBank(accountTo.getBankId());

        if (bankTo == null || bankFrom == null) {
            throw new BankNotFoundException("Bank not registerd");
        }

        if (BankStatus.DOWN.equals(bankFrom.getStatus()) || BankStatus.DOWN.equals(bankTo.getStatus())) {
            throw new BankServerDownException("Bank server down payment cant be made");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setUserFrom(userFrom.getId());
        transaction.setUserTo(userTo.getId());
        transaction.setTransferInfo(transferInfo);
        transaction.setDate(new Date());
        transaction.setId(UUID.randomUUID().toString());
        transaction.setTransactionStatus(TransactionStatus.INIT);
        transactionDao.add(transaction);
        return transaction;
    }

    public void updateTransaction(Transaction transaction) {
        transactionDao.update(transaction);
    }

    public List<Transaction> getTransactions(String userId, UserRole userRole) {
       return transactionStratergyServices.stream().filter(iTransactionStratergyService -> iTransactionStratergyService.isApplicable(userRole)).findFirst().orElse(null).getTransactions(userId);
    }

    public List<Transaction> getTransactionsOfAccount(String userId, String accountId) {
        return transactionDao.getForUserAndAccount(userId, accountId);
    }
}
