package com.shop.core.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ShopRuntimeExption{

    public ProductNotFoundException(String notification) {
        super(notification, HttpStatus.NOT_FOUND.value());
    }
}
