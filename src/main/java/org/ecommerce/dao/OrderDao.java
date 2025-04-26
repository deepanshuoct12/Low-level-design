package org.ecommerce.dao;

import org.ecommerce.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void updateOrder(Order order) {
        orders.removeIf(o -> o.getId().equals(order.getId())); // removing old order from db
        orders.add(order); // inserting new order
    }
}
