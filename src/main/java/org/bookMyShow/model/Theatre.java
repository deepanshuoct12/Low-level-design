package org.bookMyShow.model;

import lombok.Data;
import org.bookMyShow.enums.City;

import java.util.List;

@Data
public class Theatre {
    private String id;
    private List<Show> shows;
    private List<Screen> screens;
    private City city;
}
