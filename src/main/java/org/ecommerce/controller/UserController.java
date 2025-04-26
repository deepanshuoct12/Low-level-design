package org.ecommerce.controller;

import org.ecommerce.dao.UserDao;
import org.ecommerce.model.User;
import org.ecommerce.service.UserService;

public class UserController {
    private UserService userService = new UserService();

    public User createUser(String name, String emailId, String role) {
        User user = userService.addUser(name, emailId, role);
        return user;
    }
}
