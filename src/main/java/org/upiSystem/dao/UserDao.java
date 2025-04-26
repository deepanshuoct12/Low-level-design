package org.upiSystem.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.UserNotFoundException;
import org.upiSystem.model.Account;
import org.upiSystem.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDao {
    private static List<User> users = new ArrayList<>();
    private HashMap <String, User> idIndex = new HashMap<>();
    private HashMap <String, User> nameIndex = new HashMap<>();
    private HashMap <String, User> phoneNumberIndex = new HashMap<>();
    private HashMap <String, User> accountNumberIndex = new HashMap<>();

    public void add(User user) throws InvalidInputException {
        if (user == null) {
          throw new InvalidInputException("Null user provided");
        }

        users.add(user);
        updateIndexes(user);
    }

    private void updateIndexes(User user) {
        idIndex.put(user.getId(), user);
        nameIndex.put(user.getName(), user);
        phoneNumberIndex.put(user.getPhoneNumber(), user);
        List<Account> accounts = user.getAccounts();
        if (CollectionUtils.isNotEmpty(accounts)) {
            for (Account account : accounts) {
                accountNumberIndex.put(account.getAccountNumber(), user);
            }
        }
    }

    public List<User> getAll() {
        return users;
    }

    public User getById(String id) throws InvalidInputException, UserNotFoundException {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidInputException("Invalid id provided : " + id);
        }


        User user = idIndex.get(id);
        if (user == null) {
            throw new UserNotFoundException("No user found : " + id);
        }

        return user;
    }

    public User getByName(String userName) throws InvalidInputException, UserNotFoundException {
        if (StringUtils.isEmpty(userName)) {
            throw new InvalidInputException("Invalid userName provided : " + userName);
        }


        User user = nameIndex.get(userName);
        if (user == null) {
            throw new UserNotFoundException("No user found : " + userName);
        }

        return user;
    }

    public User getByPhoneNumber(String phoneNumber) throws InvalidInputException {
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new InvalidInputException("Invalid phoneNumber provided : " + phoneNumber);
        }

        User user = phoneNumberIndex.get(phoneNumber);
        return user;
    }

    public User getByAccountNumber(String accountNumber) throws InvalidInputException, UserNotFoundException {
        if (StringUtils.isEmpty(accountNumber)) {
            throw new InvalidInputException("Invalid accountNumber provided : " + accountNumber);
        }


        User user = accountNumberIndex.get(accountNumber);
        return user;
    }


    public void updateUser(User user) throws UserNotFoundException {
        User userInDb = idIndex.get(user.getId());
        if (userInDb == null) {
            throw new UserNotFoundException("No user found : " + userInDb.getId());
        }

        users.removeIf(userObj -> userObj.getId().equals(user.getId()));
        users.add(user);
        updateIndexes(user);
    }
}
