package com.test.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * The Class GlobalExceptionHandler handles all exception thrown by application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handle no such repository exists exception.
	 *
	 * @param ex the ex
	 * @return the error response
	 */
	@ExceptionHandler(value = NoSuchRepositoryExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoSuchRepositoryExistsException(NoSuchRepositoryExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	/**
	 * Handle no resource found exception.
	 *
	 * @param ex the ex
	 * @return the error response
	 */
	@ExceptionHandler(value = NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoResourceFoundException(NoResourceFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Invalid API Endpoint");
	}

}
