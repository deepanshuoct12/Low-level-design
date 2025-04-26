package org.musicStreamingPlatform.dao;

import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.Favourite;
import org.musicStreamingPlatform.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavouriteDao {
    private List <Favourite> favourites = new ArrayList<>();
    private HashMap<String, Favourite> userIdIndex = new HashMap<>();
    private HashMap<String, Favourite> idIndex = new HashMap<>();

    public void add(Favourite favourite) {
       favourites.add(favourite);
       userIdIndex.put(favourite.getUserId(), favourite);
       idIndex.put(favourite.getId(), favourite);
    }

    public Favourite get(String id) {
      return idIndex.get(id);
    }

    public Favourite byUserId(String userId) {
        return userIdIndex.get(userId);
    }

    public void update(Favourite favourite) throws InvalidInputException {
        Favourite favouriteInDb = idIndex.get(favourite.getId());
        if (favouriteInDb == null) {
            throw new InvalidInputException("No favourite found : " + favourite.getId());
        }

        favourites.removeIf(favObj -> favObj.getId().equals(favourite.getId()));
        favourites.add(favourite);
        idIndex.put(favourite.getId(), favourite);
        userIdIndex.put(favourite.getId(), favourite);
    }
}
