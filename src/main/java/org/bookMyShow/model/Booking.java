package org.bookMyShow.model;

import lombok.Data;

import java.util.List;

@Data
public class Booking {
    private Show show;
    private List<Seat> seats;
    private Payment payment;
}
