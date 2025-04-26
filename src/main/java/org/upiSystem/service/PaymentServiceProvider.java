package org.upiSystem.service;

import org.upiSystem.constants.BankStatus;
import org.upiSystem.constants.TransactionStatus;
import org.upiSystem.constants.UserRole;
import org.upiSystem.constants.UserStatus;
import org.upiSystem.exception.InActiveAccountException;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.PendingStatusException;
import org.upiSystem.model.*;


import java.util.List;


public class PaymentServiceProvider implements IPaymentServiceProvider {

    private BankService bankService = new BankService();

    private UserService userService = new UserService();

    private TransactionService transactionService = new TransactionService();

    @Override
    public void linkBankAccount(String userId, String bankId, String accountNumber, Long initialBalance) {
        try {
            User user = userService.getUser(userId);
            Bank bank = bankService.getBank(bankId);

            if (user == null || bank == null) {
                throw new InvalidInputException("User or bank not registered : " + userId + " , " + bankId);
            }

            User userObj = userService.getUserByAccountNumber(accountNumber);
            if (userObj != null) {
                throw new InvalidInputException("User with same account number already exists : " + accountNumber);
            }

            Account account =  new Account(bankId, accountNumber, initialBalance);
            user.getAccounts().add(account);
            userService.updateUser(user);
            System.out.println("Bank account linked successfully");
        } catch (Exception exception) {
            System.out.println("Not able to link bank account : " + userId);
        }
    }

    /**
     * transferFrom: it can be user account number, phone number, username
     * transferTo: it can be user account number, phone number, username
     */
    @Override
    public void makePayment(TransferInfo transferInfo) {
        for (int retry = 1; retry <= 3; retry++) {
            Transaction transaction = null;
            try {
                String transferFrom = transferInfo.getTransferFrom();
                String transferTo = transferInfo.getTransferTo();
                Long amount = transferInfo.getAmount();
                User userFrom = userService.getUser(transferFrom);
                User userTo = userService.getUser(transferTo);
                if (userFrom.getStatus().equals(UserStatus.INACTIVE) || userTo.getStatus().equals(UserStatus.INACTIVE)) {
                    throw new InActiveAccountException("Payment cant be made to/from Inactive account");
                }

                transaction = transactionService.addTransaction(transferInfo, userFrom, userTo, amount);
                // if by any chance of payment goes in pending status
                if (Math.random() < 0.1) {
                    throw new PendingStatusException("Payment under process");
                }

                userService.debitAmount(userFrom, transferInfo, amount);
                userService.creditAmount(userTo, transferInfo, amount);
                userFrom.getTransactionIds().add(transaction.getId());
                userTo.getTransactionIds().add(transaction.getId());
                userService.updateUser(userFrom);
                userService.updateUser(userTo);

                Thread.sleep(3000); // Simulating payment under process
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
                transactionService.updateTransaction(transaction);
                System.out.println("Payment done from user : " + userFrom.getName() + " to " + userTo.getName());
                break;
            } catch (PendingStatusException exception) {
                System.out.println("Retrying payment : " + retry);
            } catch (Exception exception) {
                if (transaction != null) {
                    transaction.setTransactionStatus(TransactionStatus.FAILED);
                    transactionService.updateTransaction(transaction);
                }

                System.out.println("Unable to make Payment : " + transferInfo.getTransferFrom() + " ,  " + transferInfo.getTransferTo());
                 break;
            }
        }
    }

    @Override
    public List<Transaction> getTransactionForUserAccount(String userId, String accountId) {
        return transactionService.getTransactionsOfAccount(userId, accountId);
    }

    @Override
    public List<Transaction> getAllTransactionForUser(String userId) {
       return transactionService.getTransactions(userId, UserRole.SENDER);
    }

    @Override
    public List<Transaction> searchTransactions(String userId, UserRole userRole) {
        return transactionService.getTransactions(userId, userRole);
    }

    @Override
    public User onBoardUser(String id, String userName, String phoneNumber, UserStatus userStatus) {
        User user = null;
       try {
           user =  userService.registerUser(id, userName, phoneNumber, userStatus);
           System.out.println("User registered successfully " + userName);
       } catch (InvalidInputException exception) {
           System.out.println("Unable to add user");
       }

       return user;
    }

    @Override
    public void registerBank(String bankId, String bankName, BankStatus status) {
        try {
            bankService.registerBank(bankId, bankName, status);
        } catch (Exception exception) {
            System.out.println("Not able to register bank : " + bankName);
        }

    }
}
