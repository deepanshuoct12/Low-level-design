package org.musicStreamingPlatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private List <String> friends;

}
