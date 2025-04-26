package org.parkinglot.model;

public class Price {
    private Stratergy stratergy;

    public Price(Stratergy stratergy) {
        this.stratergy = stratergy;
    }

    public void computePrice(Stratergy stratergy) {
        stratergy.computePrice();
    }
}
