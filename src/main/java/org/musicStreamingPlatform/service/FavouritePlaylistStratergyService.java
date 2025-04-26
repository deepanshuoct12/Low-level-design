package org.musicStreamingPlatform.service;

import org.musicStreamingPlatform.dao.FavouriteDao;
import org.musicStreamingPlatform.dao.PlayListDao;
import org.musicStreamingPlatform.dao.TrackDao;
import org.musicStreamingPlatform.dao.UserDao;
import org.musicStreamingPlatform.enums.FavouriteKey;
import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.Favourite;
import org.musicStreamingPlatform.model.User;

import java.util.List;

import static org.musicStreamingPlatform.enums.FavouriteKey.PLAYLIST;


public class FavouritePlaylistStratergyService implements IFavouriteStatergyService{
    private FavouriteDao favouriteDao = new FavouriteDao();
    private UserDao userDao = new UserDao();
    private PlayListDao playListDao = new PlayListDao();

    @Override
    public boolean isApplicable(FavouriteKey favouriteKey) {
        return PLAYLIST.equals(favouriteKey);
    }


    @Override
    public void markFavourite(String userId, List<String> ids) throws InvalidInputException {
        User user = userDao.getById(userId); // validate
        for (String id: ids) {
            playListDao.getById(id);
        }

        Favourite favourite = favouriteDao.byUserId(userId);

        if (favourite == null) {      // if nothing has been added to users favourite
            favourite = new Favourite();
        }

        favourite.getPlayListIds().addAll(ids);
        favouriteDao.update(favourite);
    }
}
