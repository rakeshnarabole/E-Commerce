package com.ecommerce.demo.exceptions;

public class OrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 826673506902060854L;

	public OrderException() {

	}

	public OrderException(String message) {
		super(message);
	}

}
