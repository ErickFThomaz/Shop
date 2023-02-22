package com.shop.core.exception.resource;

public class ExceptionMessage {

	private String message;

	public ExceptionMessage(final String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
