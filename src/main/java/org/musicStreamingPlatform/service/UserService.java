package org.musicStreamingPlatform.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.musicStreamingPlatform.dao.UserDao;
import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.User;

import java.util.List;


public class UserService {
    private UserDao userDao = new UserDao();

    public void registerUser(String id, String name, List<String> friends) throws InvalidInputException {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(name) || CollectionUtils.isEmpty(friends)) {
            throw new InvalidInputException("Invalid user info");
        }

        User user = new User(id, name, friends);
        userDao.add(user);
    }

    public User getUser(String userId) throws InvalidInputException {
        User user = userDao.getById(userId);
        if (user == null) {
            throw new InvalidInputException("user not found : " + userId);
        }

        return  user;
    }

    public void updateUser(User user) throws InvalidInputException {
        userDao.update(user);
    }
}
