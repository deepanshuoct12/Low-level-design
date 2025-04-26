package org.ecommerce.service;

import org.ecommerce.constants.PaymentStatus;
import org.ecommerce.dao.PaymentDao;
import org.ecommerce.dao.ProductDao;
import org.ecommerce.model.Order;
import org.ecommerce.model.Payment;
import org.ecommerce.model.Product;

import java.util.List;

public class PaymentService {
    private PaymentDao paymentDao = new PaymentDao();
    private ProductDao productDao = new ProductDao();

    public Payment makePayment(String userId, Order order, Integer price) {
        List<String> productIds = order.getProductIds();
        List<Product> products = productDao.getProducts(productIds);
        Integer sum = products.stream().mapToInt(Product::getPrice).sum();
        Payment payment = new Payment(userId, order.getId(), PaymentStatus.SUCCESS.name());

        if (sum > price) {
            payment.setStatus(PaymentStatus.FAILED.name());
        }

        paymentDao.addPayment(payment);
        return payment;
    }
}
