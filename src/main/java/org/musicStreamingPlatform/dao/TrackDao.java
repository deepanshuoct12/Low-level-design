package org.musicStreamingPlatform.dao;

import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class TrackDao {
    private static List<Track> tracks = new ArrayList<>();
    private HashMap<String, Track> idIndex = new HashMap<>();

    public void add(Track track) {
        tracks.add(track);
        idIndex.put(track.getId(), track);
    }

    public Track getById(String id) throws InvalidInputException {
        Track track = idIndex.get(id);
        if (track == null) {
            throw  new InvalidInputException("Invalid input");
        }

        return track;
    }
}
