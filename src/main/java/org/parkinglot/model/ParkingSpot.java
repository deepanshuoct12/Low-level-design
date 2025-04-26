package org.parkinglot.model;

import lombok.Data;

@Data
public class ParkingSpot {
    private Integer id;
    private Boolean isEmpty;
    private Vehicle vehicle;
    private Integer price;

    public ParkingSpot() {
    }

    public ParkingSpot(Integer id, Boolean isEmpty, Vehicle vehicle, Integer price) {
        this.id = id;
        this.isEmpty = isEmpty;
        this.vehicle = vehicle;
        this.price = price;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        isEmpty = false;
    }

    public void removeVehicle() {
        vehicle = null;
        isEmpty = true;
    }
}
