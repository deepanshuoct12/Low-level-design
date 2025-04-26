package org.ecommerce.dao;

import org.ecommerce.model.Cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CartDao {
    private static List<Cart> cartList = new ArrayList<>();

    public void addCart(Cart cart) {
        cartList.add(cart);
    }

    public Cart getCart(String userId) {
        List<Cart> carts =  cartList.stream().filter(cart -> cart.getUserId().equals(userId)).collect(Collectors.toList());
        if (!carts.isEmpty()) {
            return carts.get(0);
        }

        return null;
    }
}
