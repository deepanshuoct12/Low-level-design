package org.upiSystem.service;

import org.upiSystem.config.StratergyConfig;
import org.upiSystem.dao.RestaurantDao;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.exception.RestaurantNotFound;
import org.upiSystem.model.Food;
import org.upiSystem.model.Order;
import org.upiSystem.model.Restaurant;


import java.util.ArrayList;
import java.util.List;


public class RestaurantService {

    private RestaurantDao    restaurantDao;
    private List<org.upiSystem.service.IRestrauntStratergyService> restrauntStratergies;

    public RestaurantService() {
        restaurantDao = new RestaurantDao();
        restrauntStratergies = new ArrayList<>();

        restrauntStratergies.add(new org.upiSystem.service.PriceBasedRestrauntStratergyService());
        restrauntStratergies.add(new org.upiSystem.service.RatingBasedRestrauntStratergyService());
    }

    public void registerRestaurant(List<Restaurant> restaurants) {
        for (Restaurant restaurant : restaurants) {
            try {
                restaurantDao.addRestaurant(restaurant);
            } catch (InvalidInputException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Restaurant getRestaurant(Order order, StratergyConfig stratergyConfig) throws InvalidInputException, RestaurantNotFound {
        if (order == null || stratergyConfig == null) {
            throw new InvalidInputException();
        }


        Restaurant restaurant =  fetchRestaurantBasedOnStratergy(stratergyConfig).getRestaurant(order);
        if (restaurant == null) {
            throw new RestaurantNotFound();
        }

        return restaurant;
    }


    /*
        stratergy desing pattern
     */
    private IRestrauntStratergyService fetchRestaurantBasedOnStratergy(StratergyConfig stratergyConfig) {
       return restrauntStratergies.stream().filter(restrauntStratergy -> restrauntStratergy.isApplicable(stratergyConfig)).findAny().get();
    }

    public void updateMenu(String id, List<Food> items) {
        try {
            Restaurant restaurant = restaurantDao.findById(id);
            restaurant.getMenu().addAll(items);
            restaurantDao.update(restaurant);
        } catch (InvalidInputException | RestaurantNotFound ex) {
            System.out.println("Unable to update restaurant menu : " + id);
        }
    }
}
