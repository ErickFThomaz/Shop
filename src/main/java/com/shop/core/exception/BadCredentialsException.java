package com.shop.core.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends ShopRuntimeExption {

	public BadCredentialsException(String message) {
		super(message, HttpStatus.UNAUTHORIZED.value());
	}

}
