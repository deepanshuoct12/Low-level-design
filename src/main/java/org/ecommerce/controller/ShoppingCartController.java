package org.ecommerce.controller;

import org.ecommerce.exception.ShoppingCartException;
import org.ecommerce.exception.UserNotFoundException;
import org.ecommerce.model.Cart;
import org.ecommerce.service.ShoppingCartService;

import java.util.List;

public class ShoppingCartController {
    private ShoppingCartService shoppingCartService = new ShoppingCartService();

    public String purchase(String userId, Integer price) {
        try {
            Cart cart = shoppingCartService.getCart(userId);
            shoppingCartService.checkoutCart(cart, price);
            return "DONE";
        } catch (UserNotFoundException exception) {
            System.out.println("Cant process request for user  : " +  userId);
            return "FAILED";
        }

    }


    public String addToCart(String userId, List<String> productIds) {
        try {
            Cart cart = shoppingCartService.addToCart(userId, productIds);
            return "DONE";
        } catch (UserNotFoundException exception) {
            System.out.println("cant add to cart for user : " + userId + ", reason : " + exception.getMessage());
        }

        return null;
    }
}
