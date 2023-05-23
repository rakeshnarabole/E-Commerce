package com.ecommerce.demo.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.ecommerce.demo.models.Address;

public class CustomerGetInfoDto {

	@NotNull(message = "First Name cannot be NULL")
	private String firstName;

	@NotNull(message = "Last Name cannot be NULL")
	private String lastName;

	@Email(message = "Email address is not valid")
	@NotNull(message = "Email Name cannot be NULL")
	private String email;

	@NotNull
	private String phone;

	private Boolean isArchive = false;

	private List<Address> address;

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

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public CustomerGetInfoDto(@NotNull(message = "First Name cannot be NULL") String firstName,
			@NotNull(message = "Last Name cannot be NULL") String lastName,
			@Email(message = "Email address is not valid") @NotNull(message = "Email Name cannot be NULL") String email,
			@NotNull String phone, Boolean isArchive, List<Address> address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.isArchive = isArchive;
		this.address = address;
	}

	public CustomerGetInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
