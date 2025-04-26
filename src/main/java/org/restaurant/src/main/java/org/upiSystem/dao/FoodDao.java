package org.upiSystem.dao;

import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    public static List<Food> foods = new ArrayList<>();

    public void addFood(Food food) throws InvalidInputException {
        if (food == null) {
            throw new InvalidInputException();
        }

        foods.add(food);
    }
}
