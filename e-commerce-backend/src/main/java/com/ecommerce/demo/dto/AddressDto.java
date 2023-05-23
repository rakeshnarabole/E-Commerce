package com.ecommerce.demo.dto;

public class AddressDto {

	private int addressId;
	private int streetNum;
	private String city;
	private String state;
	private int pincode;
	private String country;
	

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

	public AddressDto(int addressId, int streetNum, String city, String state, int pincode, String country) {
		super();
		this.addressId = addressId;
		this.streetNum = streetNum;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
	}

	public AddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
