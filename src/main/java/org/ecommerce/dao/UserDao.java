package org.ecommerce.dao;

import org.ecommerce.constants.UserRole;
import org.ecommerce.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    private static List<User> users = new ArrayList();

    public User addUser(String name, String emailId, String role) {
        User user = null;
        if (UserRole.BUYER.name().equals(role)) {
             user = new User(name, emailId, UserRole.BUYER.name());
        } else {
             user = new User(name, emailId, UserRole.SELLER.name());
        }

        users.add(user);
        return user;
    }

    public User getUser(String userId) {
       List<User> userList  = users.stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
       if (userList.isEmpty()) {
           return null;
       }

       return userList.get(0);
    }
}

