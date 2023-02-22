package com.shop.core.exception;

import lombok.Getter;

@Getter
public class ShopRuntimeExption extends RuntimeException {

	private final String notification;

	private final Integer errorCode;

	public ShopRuntimeExption(String notification, Integer errorCode) {
		super(new Throwable(notification));
		this.notification = notification;
		this.errorCode = errorCode;
	}

	public ShopRuntimeExption(String notification, Integer errorCode, boolean enableSuppression) {
		super(notification, null, enableSuppression, false);
		this.notification = notification;
		this.errorCode = errorCode;
	}

	public ShopRuntimeExption(Throwable cause, String notification, Integer errorCode) {
		super(cause);
		this.notification = notification;
		this.errorCode = errorCode;
	}

}
