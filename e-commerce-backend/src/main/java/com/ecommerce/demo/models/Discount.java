package com.ecommerce.demo.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "discount")
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int discountId;
	private String name;
	private String description;
	private float discountPercentage;
	private String festival;
	private Date fromDate;
	private Date toDate;
	private Boolean isArchive = false;

	@OneToOne(mappedBy = "discount", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("discount")
	private Product product;

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
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

	public float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public Discount() {
		super();

	}

	public Discount(int discountId, String name, String description, float discountPercentage, String festival,
			Date fromDate, Date toDate, Boolean isArchive) {
		super();
		this.discountId = discountId;
		this.name = name;
		this.description = description;
		this.discountPercentage = discountPercentage;
		this.festival = festival;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.isArchive = isArchive;
	}

}
