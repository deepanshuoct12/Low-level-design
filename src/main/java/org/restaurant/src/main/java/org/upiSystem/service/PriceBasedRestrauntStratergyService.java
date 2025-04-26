package org.upiSystem.service;

import org.upiSystem.config.StratergyConfig;
import org.upiSystem.dao.RestaurantDao;
import org.upiSystem.model.Food;
import org.upiSystem.model.Order;
import org.upiSystem.model.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriceBasedRestrauntStratergyService implements IRestrauntStratergyService {

    private RestaurantDao restaurantDao = new RestaurantDao();
    private long maxPrice = 10000000l; // max limit

    @Override
    public Restaurant getRestaurant(Order order) {
        Restaurant restaurantObj = null;

        List<Restaurant> restaurants = getAllRestraunts();
        for (Restaurant restaurant : restaurants) {
            List<Food> items = order.getItems();
            List<Food> menu = restaurant.getMenu();
            Map<String, Food> foodMap = menu.stream().collect(Collectors.toMap(Food::getId, food -> food, (existing, replacement) -> existing));
            boolean present = items.stream().allMatch(food -> foodMap.containsKey(food.getId()));

            if (present) {
                long priceSum = menu.stream().mapToLong(Food::getPrice).sum();
                if (priceSum < maxPrice && restaurant.getCapacity() > 0) {
                    maxPrice = priceSum;
                    restaurantObj = restaurant;
                }
            }
        }

        if (restaurantObj != null) {
            restaurantObj.decrementCapacity();
        }

        return restaurantObj;
    }

    @Override
    public List<Restaurant> getAllRestraunts() {
        return restaurantDao.findAll();
    }

    @Override
    public boolean isApplicable(StratergyConfig stratergyConfig) {
        return StratergyConfig.PRICE.equals(stratergyConfig);
    }
}
