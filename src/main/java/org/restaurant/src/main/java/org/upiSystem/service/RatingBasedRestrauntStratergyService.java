package org.upiSystem.service;

import org.upiSystem.config.StratergyConfig;
import org.upiSystem.dao.RestaurantDao;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.Food;
import org.upiSystem.model.Order;
import org.upiSystem.model.Restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RatingBasedRestrauntStratergyService implements IRestrauntStratergyService {

    private RestaurantDao restaurantDao = new RestaurantDao();

    @Override
    public List<Restaurant> getAllRestraunts() {
        return restaurantDao.findAll();
    }

    @Override
    public boolean isApplicable(StratergyConfig stratergyConfig) {
        return StratergyConfig.RATING.equals(stratergyConfig);
    }

    @Override
    public Restaurant getRestaurant(Order order) throws InvalidInputException {
        if (order == null) {
            throw new InvalidInputException();
        }

        List <Restaurant> restaurants = getAllRestraunts();
        Restaurant restaurant = null;
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return Long.compare(r2.getRating(), r1.getRating()); // Descending order
            }
        });

        restaurant = restaurants.get(0);
        List<Food> items = order.getItems();
        List<Food> menu = restaurant.getMenu();
        Map<String, Food> foodMap = menu.stream().collect(Collectors.toMap(Food::getId, food -> food, (existing, replacement) -> existing));
        boolean present = items.stream().allMatch(food -> foodMap.containsKey(food.getId()));
        if (present) {
            restaurant.decrementCapacity();
            return restaurant;
        }

      return null;
    }
}
