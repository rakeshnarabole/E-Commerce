package com.ecommerce.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class OrdersDto {

	@NotNull
	private int orderId;
	@NotNull
	private int customerId;

	private LocalDate orderDate;
	private LocalDate deliveryDate;
	@NotNull
	private double totalPrice;

	private String orderStatus;
	private Boolean isArchive = false;

	private List<OrderProductDto> orderProducts;

	private List<CartItemDto> orderCartItems = new ArrayList<>();

	public List<CartItemDto> getOrdercartItems() {
		return orderCartItems;
	}

	public void setOrderCartItems(List<CartItemDto> orderCartItems) {
		this.orderCartItems = orderCartItems;
	}

	public List<OrderProductDto> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductDto> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public OrdersDto(@NotNull int orderId, @NotNull int customerId, LocalDate orderDate, LocalDate deliveryDate,
			@NotNull double totalPrice, String orderStatus, Boolean isArchive) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.isArchive = isArchive;

	}

	public OrdersDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
