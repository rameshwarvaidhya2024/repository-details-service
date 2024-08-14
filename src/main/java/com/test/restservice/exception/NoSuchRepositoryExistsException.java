package com.test.restservice.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class NoSuchRepositoryExistsException handles exception thrown by git
 * repo endpoint when owner / repository is not available
 */
public class NoSuchRepositoryExistsException extends RuntimeException {

	private static final long serialVersionUID = -7908029223645806726L;

	private String message;

	public NoSuchRepositoryExistsException() {
	}

	public NoSuchRepositoryExistsException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
