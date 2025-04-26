package org.parkinglot.service;

import lombok.Data;
import org.parkinglot.model.TwoWheelerParkingSpot;

import java.util.List;

@Data
public class TwoWheelerParkingManager implements ParkingLotManager {
    private List<TwoWheelerParkingSpot> parkingSpotList;

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
