package org.musicStreamingPlatform.dao;

import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.Album;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlbumDao {
    private List<Album> albums = new ArrayList<>();
    private HashMap<String, Album> idIndex = new HashMap<>();

    public void add(Album album) {
        albums.add(album);
        idIndex.put(album.getId(), album);
    }


    public Album get(String id) throws InvalidInputException {
        if (idIndex.get(id) == null) {
            throw new InvalidInputException("Invalid input");
        }

        return idIndex.get(id);
    }
}
