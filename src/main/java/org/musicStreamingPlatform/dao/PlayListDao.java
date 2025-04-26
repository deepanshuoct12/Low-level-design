package org.musicStreamingPlatform.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.musicStreamingPlatform.exception.InvalidInputException;
import org.musicStreamingPlatform.model.PlayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * we are considering single user single playlist here
 */
public class PlayListDao {
    private static List <PlayList> playLists = new ArrayList<>();
    private HashMap<String, PlayList> idIndex = new HashMap<>();
    private HashMap<String, PlayList> userIdIndex = new HashMap<>();

    public void add(PlayList playList) throws InvalidInputException {
        if (StringUtils.isEmpty(playList.getId()) || StringUtils.isEmpty(playList.getUserId()) || CollectionUtils.isEmpty(playList.getTracks())) {
            throw new InvalidInputException("Input not correct");
        }

        playLists.add(playList);
        idIndex.put(playList.getId(), playList);
        userIdIndex.put(playList.getUserId(), playList);
    }

    public PlayList getByUserId(String id) {
       return userIdIndex.get(id);
    }

    public PlayList getById(String id) throws InvalidInputException {
        PlayList playList =  idIndex.get(id);
        if (playList == null) {
            throw new InvalidInputException("Invalid playlist id");
        }

        return playList;
    }
}
