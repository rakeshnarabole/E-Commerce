package com.ecommerce.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;

	@NotNull(message = "First Name cannot be NULL")
	private String firstName;

	@NotNull(message = "Last Name cannot be NULL")
	private String lastName;

	@NotNull(message = "Email cannot be NULL")
	private String email;

	private String phone;

	private Boolean isArchive = false;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Cart customerCart;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("customer")
	private List<Address> addressList = new ArrayList<>();

	// Establishing Customer - Order relationship
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("customer")
	private List<Orders> orderList = new ArrayList<>();

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public Cart getCustomerCart() {
		return customerCart;
	}

	public void setCustomerCart(Cart customerCart) {
		this.customerCart = customerCart;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	

	public Customer(int customerId, String firstName, String lastName, String email, String phone, Boolean isArchive,
			List<Address> addressList) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.isArchive = isArchive;
		this.addressList = addressList;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
