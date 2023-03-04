package com.shop.payment.method;

public interface PaymentMethodStrategy {

    void verifyPayment(String s);

    void createPayment(String s);
}
