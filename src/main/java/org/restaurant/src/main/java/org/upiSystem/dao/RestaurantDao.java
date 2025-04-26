package org.upiSystem.dao;

import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.RestaurantNotFound;
import org.upiSystem.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {
    public static List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(Restaurant restaurant) throws InvalidInputException {
        if (restaurant == null) {
            throw new InvalidInputException();
        }

        restaurants.add(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurants;
    }

    public Restaurant findById(String id) throws InvalidInputException, RestaurantNotFound {
        if (id == null) {
            throw new InvalidInputException();
        }

        Restaurant restaurant = restaurants.stream().filter(restaurantObj -> restaurantObj.getId().equals(id)).findFirst().orElse(null);
        if (restaurant == null) {
            throw new RestaurantNotFound();
        }

        return restaurant;
    }

    public void update(Restaurant restaurant) throws InvalidInputException, RestaurantNotFound {
        findById(restaurant.getId());
        restaurants.removeIf(restaurantObj -> restaurantObj.getId().equals(restaurant.getId()));
        restaurants.add(restaurant);
    }
}
