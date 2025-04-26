package org.bookMyShow.model;

import lombok.Data;
import org.bookMyShow.enums.SeatCategory;

@Data
public class Seat {
    private int seatId;
    private int row;
    private SeatCategory seatCategory;
}

