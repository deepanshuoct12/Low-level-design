package org.ecommerce.service;

import org.ecommerce.constants.OrderStatus;
import org.ecommerce.dao.OrderDao;
import org.ecommerce.model.Order;

import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public Order createOrder(List<String> productIds, String userId) {
        Order order = new Order(productIds, userId, OrderStatus.INIT.name());
        orderDao.addOrder(order);
        return order;
    }

    public void stateChange(Order order, OrderStatus orderStatus) {
        order.setStatus(orderStatus.name());
        orderDao.updateOrder(order);
    }
}
