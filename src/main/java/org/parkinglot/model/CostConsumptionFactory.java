package org.parkinglot.model;

import static org.parkinglot.Constants.FOUR_WHEELER;
import static org.parkinglot.Constants.TWO_WHEELER;

public class CostConsumptionFactory {
    public CostConsumption getCostConsumption(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case TWO_WHEELER:
                return new TwoWheelerCostConsumption();

            case FOUR_WHEELER:
                return new FourWheelerCostConsumption();
        }

        return null;
    }
}
