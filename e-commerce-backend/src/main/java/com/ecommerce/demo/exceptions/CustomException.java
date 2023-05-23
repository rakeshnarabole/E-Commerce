package com.ecommerce.demo.exceptions;

import java.io.Serial;

public class CustomException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	private int status;
	private String message;
	private Object[] parameters;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public CustomException() {
		// Empty constructor
	}

	public CustomException(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public CustomException(int status, String message, Object[] parameters) {
		this.status = status;
		this.message = message;
		this.parameters = parameters;
	}

}
