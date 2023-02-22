package com.shop.core.exception.handler;

import com.shop.core.ShopMessageSource;
import com.shop.core.exception.resource.ApiError;
import com.shop.core.exception.resource.ApiValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Order(1)
@RestControllerAdvice
@AllArgsConstructor
public class ErrorHandler {

	private final ShopMessageSource messageSource;

	private final static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";

	private static final String ERROR = "error";

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public HttpEntity<Object> handlerValidationException(MethodArgumentTypeMismatchException ex) {
		logger.error("", ex);
		return buildResponseEntity(new ApiError(BAD_REQUEST,
				"Invalid parameter: " + ex.getParameter().getParameterName() + " with value: " + ex.getValue(), ex));
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> handlePersistenceException(ConstraintViolationException ex) {
		logger.error("", ex);
		final ApiError apiError = new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), ex);

		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			apiError.addSubErros(new ApiValidationError(violation.getMessage()));
		}
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	protected ResponseEntity<Object> handlePersistenceException(MethodArgumentNotValidException ex) {
		logger.error("", ex);
		final ApiError apiError = new ApiError(BAD_REQUEST, messageSource.getMessage("validation.error"));

		for (FieldError fieldError : ex.getFieldErrors()) {
			apiError.addSubErros(new ApiValidationError(fieldError.getField(), fieldError.getRejectedValue(),
					fieldError.getDefaultMessage()));
		}
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ Exception.class, Error.class })
	public HttpEntity<Object> handlerException(Exception ex) {
		logger.error("", ex);
		return buildResponseEntity(new ApiError(INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(apiError, responseHeaders, apiError.getStatus());
	}

}
