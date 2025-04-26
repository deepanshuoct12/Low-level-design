package org.parkinglot.service;

import org.parkinglot.model.Vehicle;

import static org.parkinglot.Constants.FOUR_WHEELER;
import static org.parkinglot.Constants.TWO_WHEELER;

public class ParkingLotManagerFactory {
    public ParkingLotManager getParkingLotManager(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case FOUR_WHEELER:
                return new FourWheelerParkingManager();

            case TWO_WHEELER:
                return new TwoWheelerParkingManager();
        }

        return null;
    }
}
