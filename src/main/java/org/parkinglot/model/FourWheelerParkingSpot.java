package org.parkinglot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FourWheelerParkingSpot extends ParkingSpot {
    public FourWheelerParkingSpot(Integer id, Boolean isEmpty, Vehicle vehicle, Integer price) {
        super(id, isEmpty, vehicle, 40);
    }
}
