package org.bookMyShow.model;

import lombok.Data;

import java.util.List;

@Data
public class Show {
    private int id;
    private Movie movie;
    private Screen screen;
    private int showStartTime;
    private List<Integer> seatIds;
}
