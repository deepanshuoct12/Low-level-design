package org.bookMyShow.model;

import lombok.Data;

import java.util.List;

@Data
public class Screen {
    private String id;
    private List<Seat> seats;
}
