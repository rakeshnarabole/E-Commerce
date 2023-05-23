package com.ecommerce.demo.dto;

import javax.validation.constraints.NotNull;

import com.ecommerce.demo.models.Product;

public class ProductDto {

	@NotNull
	private int productId;
	@NotNull
	private String name;
	private String description;
	private Double price;
	private int stock;
	private int catagoryId;
	private int discountId;
	private Boolean isArchive = false;

	private Double discountPrice;

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice2) {
		this.discountPrice = discountPrice2;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public ProductDto() {
		super();

	}

	public ProductDto(int productId, String name, String description, Double price, int stock, int catagoryId,
			int discountId, Boolean isArchive) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.catagoryId = catagoryId;
		this.discountId = discountId;
		this.isArchive = isArchive;
	}

	public ProductDto(Product product) {

	}

}
