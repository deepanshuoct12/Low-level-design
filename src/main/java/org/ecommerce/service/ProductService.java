package org.ecommerce.service;

import org.ecommerce.dao.ProductDao;
import org.ecommerce.model.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();

    public List<Product> getProducts() {
        return productDao.getAllProducts();
    }

    public void removeProducts(List<String> productIds) {
        productDao.removeProducts(productIds);
    }
}
