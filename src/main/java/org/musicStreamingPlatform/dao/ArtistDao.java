package org.musicStreamingPlatform.dao;

import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.Artist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtistDao {
    private static List<Artist> artists = new ArrayList<>();
    private HashMap<String, Artist> idIndex = new HashMap<>();

    public void add(Artist artist) {
        artists.add(artist);
        idIndex.put(artist.getId(), artist);
    }

    public Artist getById(String id) throws InvalidInputException {
        Artist artist = idIndex.get(id);
        if (artist == null) {
            throw new InvalidInputException("Invalid input");
        }

        return artist;
    }
}
