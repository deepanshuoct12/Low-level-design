package org.upiSystem.service;

import org.upiSystem.config.StratergyConfig;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.RestaurantNotFound;
import org.upiSystem.model.Food;
import org.upiSystem.model.Order;
import org.upiSystem.model.Restaurant;

import java.util.stream.Collectors;

public class FoodOrderingService {

    private RestaurantService restaurantService = new RestaurantService();

    public void orderFood(Order order, StratergyConfig stratergyConfig)  {
        try {
            Restaurant restaurant = restaurantService.getRestaurant(order, stratergyConfig);
            System.out.println("Restaurant selected : " + restaurant.getName());
            String names = order.getItems().stream().map(Food::getName).collect(Collectors.joining(" "));
            System.out.println("Food ordered : " + names);
        } catch (InvalidInputException | RestaurantNotFound ex) {
            System.out.println("Not able to process order : " + ex.getMessage());
        }
    }
}
