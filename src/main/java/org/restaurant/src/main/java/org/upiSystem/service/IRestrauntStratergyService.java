package org.upiSystem.service;

import org.upiSystem.config.StratergyConfig;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.Order;
import org.upiSystem.model.Restaurant;

import java.util.List;

public interface IRestrauntStratergyService {
     Restaurant getRestaurant(Order order) throws InvalidInputException;

     List<Restaurant> getAllRestraunts();

     boolean isApplicable(StratergyConfig stratergyConfig);
}
