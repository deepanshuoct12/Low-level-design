package org.musicStreamingPlatform.model;

import lombok.Data;

import java.util.List;

@Data
public class PlayList {
    private String id;
    private List<String> tracks;
    private String userId;
}
