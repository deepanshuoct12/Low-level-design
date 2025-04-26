package org.ecommerce.dao;

import org.ecommerce.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    private static List<Payment> payments = new ArrayList<>();

    public Payment addPayment(Payment payment) {
        payments.add(payment);
        return payment;
    }
}
