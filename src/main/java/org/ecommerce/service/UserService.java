package org.ecommerce.service;

import org.ecommerce.dao.UserDao;
import org.ecommerce.model.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public User addUser(String name, String emailId, String role) {
        return userDao.addUser(name, emailId, role);
    }
}
