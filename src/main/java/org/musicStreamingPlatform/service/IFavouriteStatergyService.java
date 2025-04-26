package org.musicStreamingPlatform.service;

import org.musicStreamingPlatform.enums.FavouriteKey;
import org.musicStreamingPlatform.exception.InvalidInputException;

import java.util.List;

public interface IFavouriteStatergyService {
    boolean isApplicable(FavouriteKey favouriteKey);
    void markFavourite(String userId, List<String> ids) throws InvalidInputException;
}
