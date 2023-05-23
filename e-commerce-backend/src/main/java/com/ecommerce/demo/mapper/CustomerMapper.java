package com.ecommerce.demo.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.demo.dto.CustomerDto;
import com.ecommerce.demo.models.Customer;

@Component
public class CustomerMapper {

	// Convert Customer JPA Entity into CustomerDto
	public  CustomerDto mapToCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhone(customer.getPhone());
		customerDto.setIsArchive(customer.getIsArchive());
		customerDto.setAddress(customer.getAddressList());
		

		return customerDto;
	}

	 //Convert CustomerDto into Customer JPA Entity
	public  Customer mapToCustomer(CustomerDto customerDto) {
		return new Customer(customerDto.getCustomerId(), customerDto.getFirstName(), customerDto.getLastName(),
				customerDto.getEmail(), customerDto.getPhone(), customerDto.getIsArchive(), customerDto.getAddress());

	}
}
