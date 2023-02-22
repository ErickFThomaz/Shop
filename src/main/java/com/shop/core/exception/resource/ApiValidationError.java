package com.shop.core.exception.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

	private String field;

	private Object rejectedValue;

	private String message;

	public ApiValidationError(String message) {
		this.message = message;
	}

}