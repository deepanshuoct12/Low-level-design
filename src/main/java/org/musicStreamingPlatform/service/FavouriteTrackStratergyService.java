package org.musicStreamingPlatform.service;

import org.musicStreamingPlatform.dao.FavouriteDao;
import org.musicStreamingPlatform.dao.TrackDao;
import org.musicStreamingPlatform.dao.UserDao;
import org.musicStreamingPlatform.enums.FavouriteKey;
import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.Favourite;
import org.musicStreamingPlatform.model.User;

import java.util.List;

import static org.musicStreamingPlatform.enums.FavouriteKey.TRACK;

public class FavouriteTrackStratergyService implements IFavouriteStatergyService {
    private FavouriteDao favouriteDao = new FavouriteDao();
    private TrackDao trackDao = new TrackDao();
    private UserDao userDao = new UserDao();

    @Override
    public boolean isApplicable(FavouriteKey favouriteKey) {
        return TRACK.equals(favouriteKey);
    }

    @Override
    public void markFavourite(String userId, List<String> ids) throws InvalidInputException {
        User user = userDao.getById(userId); // validate
        for (String id: ids) {
            trackDao.getById(id);
        }

        Favourite favourite = favouriteDao.byUserId(userId);

        if (favourite == null) {      // if nothing has been added to users favourite
            favourite = new Favourite();
        }

        favourite.getTrackIds().addAll(ids);
        favouriteDao.update(favourite);
    }
}
