package com.ecommerce.demo.dto;

public class OrderDto {

	private int customerId;

	private int addressId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public OrderDto(int customerId, int addressId) {
		super();
		this.customerId = customerId;
		this.addressId = addressId;
	}

	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
