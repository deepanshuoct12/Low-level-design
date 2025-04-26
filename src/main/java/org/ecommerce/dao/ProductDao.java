package org.ecommerce.dao;

import org.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {
    private static List<Product> products = new ArrayList();

    public List<Product> getProducts(List<String> productIds) {
        HashSet<String> idsProvided = new HashSet<>(productIds);
        return products.stream().filter(product -> idsProvided.contains(product.getId())).collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public List<Product> getAllProducts() {
       return products;
    }

    public void removeProducts(List<String> productIds) {
        HashSet<String> idsProvided = new HashSet<>(productIds);
        products.removeIf(product -> idsProvided.contains(product.getId()));
    }
}
