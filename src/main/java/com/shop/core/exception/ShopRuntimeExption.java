package com.shop.core.exception;

public class ShopRuntimeExption extends RuntimeException {

	private String notification;

	private Integer errorCode;

	public ShopRuntimeExption() {
	}

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
