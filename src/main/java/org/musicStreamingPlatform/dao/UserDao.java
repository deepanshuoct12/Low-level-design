package org.musicStreamingPlatform.dao;

import org.apache.commons.lang3.StringUtils;
import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDao {
    private static List<User> users = new ArrayList<>();
    private HashMap<String, User> idIndex = new HashMap<>();

    public void add(User user) throws InvalidInputException {
        if (user == null) {
            throw new InvalidInputException("null user");
        }

        users.add(user);
        idIndex.put(user.getId(), user);
    }

    public List<User> getAll() {
        return users;
    }

    public User getById(String id) throws InvalidInputException {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidInputException("empty/ null user id");
        }

        return idIndex.get(id);
    }

    public void update(User user) throws InvalidInputException {
        User userInDb = idIndex.get(user.getId());
        if (userInDb == null) {
            throw new InvalidInputException("No user found : " + userInDb.getId());
        }

        users.removeIf(userObj -> userObj.getId().equals(user.getId()));
        users.add(user);
        idIndex.put(user.getId(), user);
    }
}

