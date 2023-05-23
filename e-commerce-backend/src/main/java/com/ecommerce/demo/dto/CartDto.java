package com.ecommerce.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartDto {

	
	@NotNull
	private Integer productId;


	@Min(1)
	private Integer quantity;

	private Integer customerId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	

}
