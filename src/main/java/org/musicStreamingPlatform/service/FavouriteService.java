package org.musicStreamingPlatform.service;



import org.musicStreamingPlatform.enums.FavouriteKey;
import org.musicStreamingPlatform.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class FavouriteService {
    List<IFavouriteStatergyService> iFavouriteStatergyServiceList = new ArrayList<>();

    public FavouriteService() {
        FavouriteAlbumStratergyService favouriteAlbumStratergyService = new FavouriteAlbumStratergyService();
        FavouritePlaylistStratergyService favouritePlaylistStratergyService = new FavouritePlaylistStratergyService();
        FavouriteArtistStratergyService favouriteArtistStratergyService = new FavouriteArtistStratergyService();
        FavouriteTrackStratergyService favouriteTrackStratergyService = new FavouriteTrackStratergyService();

        iFavouriteStatergyServiceList.add(favouriteAlbumStratergyService);
        iFavouriteStatergyServiceList.add(favouritePlaylistStratergyService);
        iFavouriteStatergyServiceList.add(favouriteArtistStratergyService);
        iFavouriteStatergyServiceList.add(favouriteTrackStratergyService);
    }

    public void mark(String userId, FavouriteKey favouriteKey, List<String> ids) throws InvalidInputException {
         iFavouriteStatergyServiceList.stream().filter(iFavouriteStatergyService -> iFavouriteStatergyService.isApplicable(favouriteKey)).findFirst().get().markFavourite(userId, ids);
    }
}
