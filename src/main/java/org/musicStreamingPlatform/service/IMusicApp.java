package org.musicStreamingPlatform.service;
import org.musicStreamingPlatform.enums.FavouriteKey;
import org.musicStreamingPlatform.model.PlayList;

import java.util.List;

public interface IMusicApp {
    boolean registerUser(String id, String name, List<String> friends);
    boolean createPlayList(String userId, List<String> trackIds);
    boolean addFriendsOfAUser(String userId, List<String> friendIds);
    boolean removeFriendsOfAUser(String userId, List<String> friendIds);
    PlayList getPlayListOfFriend(String friendId);
    boolean markFavourite(String userId, FavouriteKey favouriteKey, List<String> ids);
}
