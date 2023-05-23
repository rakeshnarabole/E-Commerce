package com.ecommerce.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private int streetNum;
	private String city;
	private String state;
	private int pincode;
	private String country;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	@JsonIgnoreProperties("address")
	private Customer customer;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(int streetNum) {
		this.streetNum = streetNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address() {
		super();

	}

	public Address(int addressId, int streetNum, String city, String state, int pincode, String country) {
		super();
		this.addressId = addressId;
		this.streetNum = streetNum;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
	}

}
