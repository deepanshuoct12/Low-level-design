package org.musicStreamingPlatform.model;

import lombok.Data;

import java.util.List;

@Data
public class Album {
    private List<String> trackIds;
    private String id;
}
