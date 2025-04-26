package org.parkinglot.service;

import lombok.Data;
import org.parkinglot.model.FourWheelerParkingSpot;

import java.util.List;

@Data
public class FourWheelerParkingManager implements ParkingLotManager {
    private List<FourWheelerParkingSpot> parkingSpotList;

    @Override
    public void findParkingSpace() {

    }

    @Override
    public void addParkingSpace() {

    }

    @Override
    public void removeParkingSpace() {

    }

    @Override
    public void parkVehicle() {

    }

    @Override
    public void removeVehicle() {

    }
}
