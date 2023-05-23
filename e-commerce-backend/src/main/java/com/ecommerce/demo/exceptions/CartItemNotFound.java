package com.ecommerce.demo.exceptions;

public class CartItemNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartItemNotFound() {
	}

	public CartItemNotFound(String message) {
		super(message);
	}
}
