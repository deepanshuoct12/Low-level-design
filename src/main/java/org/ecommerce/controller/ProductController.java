package org.ecommerce.controller;

import org.ecommerce.dao.ProductDao;
import org.ecommerce.model.Product;
import org.ecommerce.service.ProductService;

import java.util.List;

public class ProductController {
    private ProductService productService = new ProductService();
    private ProductDao productDao = new ProductDao();

    public List<Product> get() {
        return productService.getProducts();
    }

    public void addProduct(List<Product> products) {
        products.forEach(product ->  productDao.addProduct(product));
    }
}
