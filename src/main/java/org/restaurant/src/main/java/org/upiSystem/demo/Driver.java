package org.upiSystem.demo;

import org.upiSystem.config.StratergyConfig;
import org.upiSystem.dao.UserDao;
import org.upiSystem.model.Food;
import org.upiSystem.model.Order;
import org.upiSystem.model.Restaurant;
import org.upiSystem.model.User;
import org.upiSystem.service.FoodOrderingService;
import org.upiSystem.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    private UserDao userDao = new UserDao();
    private FoodOrderingService foodOrderingService  = new FoodOrderingService();
    private RestaurantService   restaurantService = new RestaurantService();

    private List<User> initUser() {
        List<User> users = new ArrayList<>();
        User user1 = new User("1","ab", "123345");
        User user2 = new User("2","bc", "13445");
        users.add(user1);
        users.add(user2);
        return users;
    }


    public void startExecution() {
        userDao.addUser(initUser());
        restaurantService.registerRestaurant(initRestaurant());

        Order orderAll = new Order();
        Food foodVada = new Food();
        foodVada.setId("111");
        foodVada.setName("vada");

        Food foodRice = new Food();
        foodRice.setId("112");
        foodRice.setName("rice");

        orderAll.getItems().add(foodVada);
        orderAll.getItems().add(foodRice);

        Order orderRice = new Order();
        orderRice.getItems().add(foodRice);

        foodOrderingService.orderFood(orderAll, StratergyConfig.PRICE);
        foodOrderingService.orderFood(orderRice, StratergyConfig.RATING);

        List<Food> items = List.of(new Food("113", "aloo methi", 80l), new Food("114", "paneer", 200l));
        restaurantService.updateMenu("11", items);
    }

    private List<Restaurant> initRestaurant() {
        List<Restaurant> restaurants = new ArrayList<>();
        List<Food> menu = new ArrayList<>();
        menu.add(new Food("111", "idli", 100l));
        menu.add(new Food("112", "vada", 200l));
        menu.add(new Food("113", "rice", 300l));
        Restaurant restaurant1 = new Restaurant("11", "abc", menu, 1l, 4l);

        menu = new ArrayList<>();
        menu.add(new Food("114", "idli", 400l));
        menu.add(new Food("111", "vada", 600l));
        Restaurant restaurant2 = new Restaurant("12", "def", menu, 5l, 5l);

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        return restaurants;
    }
}
