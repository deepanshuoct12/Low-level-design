package org.upiSystem.service;

import org.upiSystem.constants.BankStatus;
import org.upiSystem.constants.UserRole;
import org.upiSystem.constants.UserStatus;
import org.upiSystem.model.Transaction;
import org.upiSystem.model.TransferInfo;
import org.upiSystem.model.User;

import java.util.List;

public interface IPaymentServiceProvider {
     void linkBankAccount(String userId, String bankId, String accountNumber, Long initialBalance);

     void makePayment(TransferInfo transferInfo);

     List<Transaction> getTransactionForUserAccount(String userId, String accountId);

     List<Transaction> getAllTransactionForUser(String userId);

     List<Transaction> searchTransactions(String userId, UserRole userRole);

     User onBoardUser(String id, String userName, String phoneNumber, UserStatus userStatus);

     void registerBank(String bankId, String bankName, BankStatus status);
}
