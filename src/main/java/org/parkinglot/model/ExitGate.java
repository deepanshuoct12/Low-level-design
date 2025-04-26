package org.parkinglot.model;

import org.parkinglot.service.ParkingLotManagerFactory;

public class ExitGate implements Gate{
    private ParkingLotManagerFactory parkingLotManagerFactory;
    private CostConsumptionFactory costConsumptionFactory;

    @Override
    public void findParkingSpot() {

    }

    public void priceCal(Vehicle vehicle) {

    }

    public void removeSpot() {
           // remove vehicle also
    }
}
