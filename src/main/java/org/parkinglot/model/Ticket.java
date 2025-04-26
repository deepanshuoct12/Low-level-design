package org.parkinglot.model;

import lombok.Data;

@Data
public class Ticket {
    private Long entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
}
