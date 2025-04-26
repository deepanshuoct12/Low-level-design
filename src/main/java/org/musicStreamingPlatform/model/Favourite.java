package org.musicStreamingPlatform.model;

import lombok.Data;
import java.util.List;

@Data
public class Favourite {
    private String id;
    private String userId;
    private List <String> artistIds;
    private List <String> playListIds;
    private List <String> trackIds;
    private List <String> albumIds;
}
