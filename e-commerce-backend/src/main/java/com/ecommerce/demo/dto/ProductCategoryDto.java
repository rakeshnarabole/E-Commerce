package com.ecommerce.demo.dto;

public class ProductCategoryDto {

	private int id;
	private String name;
	private String description;

	private Boolean isArchive = false;

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ProductCategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategoryDto(int id, String name, String description, Boolean isArchive) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isArchive = isArchive;
	}

}
