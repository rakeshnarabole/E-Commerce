package com.ecommerce.demo.dto;

public class PaymentDto {

	private String paymentId;
	private String customerId;
	private String orderId;
	private String amount;
	private String paymentType;
	private String status;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDto(String paymentId, String customerId, String orderId, String amount, String paymentType,
			String status) {
		super();
		this.paymentId = paymentId;
		this.customerId = customerId;
		this.orderId = orderId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.status = status;
	}

}
