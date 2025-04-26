package org.upiSystem.dao;

import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> users = new ArrayList<>();

    public void addUser(User user) throws InvalidInputException {
        if (user == null) {
            throw new InvalidInputException();
        }

        users.add(user);
    }

    public void addUser(List<User> users) {
        for (User user :  users) {
            try {
                addUser(user);
            } catch (InvalidInputException ex) {
                System.out.println("Unable to add user");
            }
        }
    }
}
