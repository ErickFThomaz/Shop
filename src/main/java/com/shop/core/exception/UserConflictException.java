package com.shop.core.exception;

import org.springframework.http.HttpStatus;

public class UserConflictException extends ShopRuntimeExption {

	public UserConflictException(String message) {
		super(message, HttpStatus.CONFLICT.value());
	}

}
