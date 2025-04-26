package org.musicStreamingPlatform.service;

import org.musicStreamingPlatform.dao.PlayListDao;
import org.musicStreamingPlatform.enums.FavouriteKey;
import org.musicStreamingPlatform.model.PlayList;
import org.musicStreamingPlatform.model.User;

import java.util.List;
import java.util.UUID;

public class MusicApp implements IMusicApp {
    private UserService userService = new UserService();
    private PlayListDao playListDao = new PlayListDao();
    private FavouriteService favouriteService = new FavouriteService();

    @Override
    public boolean registerUser(String id, String name, List<String> friends) {
        try {
            userService.registerUser(id, name, friends);
            return true;
        } catch (Exception exception) {
            System.out.printf("Unable to add user : " + exception.getMessage());
        }

        return false;
    }

    @Override
    public boolean createPlayList(String userId, List<String> trackIds) {
        try {
            PlayList playList = new PlayList();
            playList.setUserId(userId);
            playList.setTracks(trackIds);
            String id = UUID.randomUUID().toString();
            playList.setId(id);
            userService.getUser(userId); // validate
            playListDao.add(playList);
            return true;
        } catch (Exception exception) {
            System.out.println("unable to add playlist : " + exception.getMessage());
        }

        return false;
    }

    @Override
    public boolean addFriendsOfAUser(String userId, List<String> friendIds) {
        try {
            User user = userService.getUser(userId);
            for (String id : friendIds) {
                userService.getUser(id);
            }

            user.getFriends().addAll(friendIds);
            userService.updateUser(user);
            return true;
        } catch (Exception exception) {
            System.out.println("Unable to add friends of a user : " + exception.getMessage());
        }

        return false;
    }

    @Override
    public boolean removeFriendsOfAUser(String userId, List<String> friendIds) {
        try {
            User user = userService.getUser(userId);
            for (String id : friendIds) {  // validate
                userService.getUser(id);
            }

            user.getFriends().removeIf(id -> friendIds.contains(id));
            userService.updateUser(user);
            return true;
        } catch (Exception exception) {
            System.out.println("Unable to add friends of a user : " + exception.getMessage());
        }

        return false;
    }

    @Override
    public PlayList getPlayListOfFriend(String friendId) {
        return playListDao.getByUserId(friendId);
    }

    @Override
    public boolean markFavourite(String userId, FavouriteKey favouriteKey, List<String> ids) {
     try {
         favouriteService.mark(userId, favouriteKey, ids);
         return true;
     } catch (Exception exception) {
         System.out.println("Unable to mark : " + exception.getMessage());
     }

     return false;
    }
}
