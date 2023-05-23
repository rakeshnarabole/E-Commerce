package com.ecommerce.demo.dto;

public class CartItemDto {

	private Integer cartItemId;

	private Integer cartProductId;

	private String cartProductName;

	private Integer cartItemQuantity;

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(Integer cartProductId) {
		this.cartProductId = cartProductId;
	}

	public String getCartProductName() {
		return cartProductName;
	}

	public void setCartProductName(String cartProductName) {
		this.cartProductName = cartProductName;
	}

	public Integer getCartItemQuantity() {
		return cartItemQuantity;
	}

	public void setCartItemQuantity(Integer cartItemQuantity) {
		this.cartItemQuantity = cartItemQuantity;
	}

	public CartItemDto(Integer cartItemId, Integer cartProductId, String cartProductName, Integer cartItemQuantity) {
		super();
		this.cartItemId = cartItemId;
		this.cartProductId = cartProductId;
		this.cartProductName = cartProductName;
		this.cartItemQuantity = cartItemQuantity;
	}

	public CartItemDto() {
		super();
		
	}

}
