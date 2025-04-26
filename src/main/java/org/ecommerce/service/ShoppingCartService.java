package org.ecommerce.service;

import org.ecommerce.constants.OrderStatus;
import org.ecommerce.constants.PaymentStatus;
import org.ecommerce.dao.CartDao;
import org.ecommerce.dao.UserDao;
import org.ecommerce.exception.UserNotFoundException;
import org.ecommerce.model.Cart;
import org.ecommerce.model.Order;
import org.ecommerce.model.Payment;
import org.ecommerce.model.User;


import java.util.List;

public class ShoppingCartService {
    private ProductService productService = new ProductService();
    private OrderService   orderService = new OrderService();
    private PaymentService paymentService = new PaymentService();

    private CartDao cartDao = new CartDao();
    private UserDao userDao = new UserDao();

    public Cart getCart(String userId) throws UserNotFoundException {
        validateUser(userId);
        return cartDao.getCart(userId) != null ? cartDao.getCart(userId) : null;
    }

    public Cart addToCart(String userId, List<String> productIds) throws UserNotFoundException {
        validateUser(userId);
        Cart cart = cartDao.getCart(userId);
        if (cart == null) {
            cart = new Cart(userId, productIds);
        } else {
            cart.getProductIds().addAll(productIds);
        }

        cartDao.addCart(cart);
        return cart;
    }

    private void validateUser(String userId) throws UserNotFoundException {
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
    }

    public void checkoutCart(Cart cart, Integer price) {
        Order order = orderService.createOrder(cart.getProductIds(), cart.getUserId());
        Payment payment = paymentService.makePayment(cart.getUserId(), order, price);
        if (PaymentStatus.FAILED.name().equals(payment.getStatus())) {
            orderService.stateChange(order, OrderStatus.FAILED);
            System.out.println("Order failed : " + order.getId());
        } else {
            orderService.stateChange(order, OrderStatus.PROCESSED);
            productService.removeProducts(cart.getProductIds());
            System.out.println("Order success : " + order.getId());
        }
    }
}
