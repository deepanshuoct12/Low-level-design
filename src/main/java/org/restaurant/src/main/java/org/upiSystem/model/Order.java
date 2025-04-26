package org.upiSystem.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private List<Food> items;

    public List<Food> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }
}
