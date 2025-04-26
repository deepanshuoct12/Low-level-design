package org.parkinglot.model;

import lombok.Data;

@Data
public class TwoWheelerParkingSpot extends ParkingSpot{
    public TwoWheelerParkingSpot(Integer id, Boolean isEmpty, Vehicle vehicle, Integer price) {
        super(id, isEmpty, vehicle, 10);
    }
}
