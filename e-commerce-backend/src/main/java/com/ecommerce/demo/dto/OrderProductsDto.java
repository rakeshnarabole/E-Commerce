package com.ecommerce.demo.dto;

public class OrderProductsDto {

	private int id;
	private int quantity;
	private int orderId;
	private int productId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public OrderProductsDto() {
		super();
		
	}

	public OrderProductsDto(int id, int quantity, int orderId, int productId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.orderId = orderId;
		this.productId = productId;
	}

}
