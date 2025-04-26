package org.upiSystem.service;

import org.apache.commons.lang3.StringUtils;
import org.upiSystem.constants.UserStatus;
import org.upiSystem.dao.UserDao;
import org.upiSystem.exception.InsufficientBalanceException;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.UserNotFoundException;
import org.upiSystem.model.Account;
import org.upiSystem.model.TransferInfo;
import org.upiSystem.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();


    public User registerUser(String id, String userName, String phoneNumber, UserStatus userStatus) throws InvalidInputException {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(phoneNumber)) {
            throw new InvalidInputException("Invalid input : " + id + " , " + userName + " , " + phoneNumber);
        }

        User user = userDao.getByPhoneNumber(phoneNumber);
        if (user != null) {
            throw new InvalidInputException("User already exist with phone number : " + phoneNumber);
        }

        user = User.builder().id(id).name(userName).phoneNumber(phoneNumber).status(userStatus).build();
        userDao.add(user);
        return user;
    }

    public User getUser(String userInput) throws UserNotFoundException, InvalidInputException {
        User user =  userDao.getById(userInput);
        if (user == null) {
            user = userDao.getByName(userInput);
        }

        if (user == null) {
            user = userDao.getByPhoneNumber(userInput);
        }

        if (user == null) {
            user = userDao.getByAccountNumber(userInput);
        }


        return user;
    }

    public void updateUser(User user) throws UserNotFoundException {
        userDao.updateUser(user);
    }


    public void debitAmount(User user, TransferInfo transferInfo, Long amount) throws InsufficientBalanceException {
        List<Account> accounts = user.getAccounts();
        Account accountObj = accounts.stream().filter(account -> account.getAccountNumber().equals(transferInfo.getAccountFromNumber())).findFirst().orElse(null);
        accountObj.debit(amount);
        user.setAccounts(accounts);
    }

    public void creditAmount(User user, TransferInfo transferInfo, Long amount) {
        List<Account> accounts = user.getAccounts();
        Account accountObj = accounts.stream().filter(account -> account.getAccountNumber().equals(transferInfo.getAccountToNumber())).findFirst().orElse(null);
        accountObj.credit(amount);
        user.setAccounts(accounts);
    }

    public User getUserByAccountNumber(String accountNumber) throws UserNotFoundException, InvalidInputException {
        return userDao.getByAccountNumber(accountNumber);
    }
}
