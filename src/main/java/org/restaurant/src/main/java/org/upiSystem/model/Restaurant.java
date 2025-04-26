package org.upiSystem.model;

import lombok.Data;

import java.util.List;


@Data
public class Restaurant {
    private String id;
    private String name;
    private List<Food> menu;
    private Long capacity;      // no. of order it can process
    private Long rating;

    public Restaurant(String id, String name, List<Food> menu, Long capacity, Long rating) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.capacity = capacity;
        this.rating = rating;
    }

    public void decrementCapacity() {
        this.capacity--;
    }
}
