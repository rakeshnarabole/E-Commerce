package com.ecommerce.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.AddressDto;
import com.ecommerce.demo.dto.CustomerDto;
import com.ecommerce.demo.dto.CustomerGetInfoDto;
import com.ecommerce.demo.dto.CustomerInfoDto;
import com.ecommerce.demo.models.Customer;

@Service
public interface CustomerService {

	Customer createCustomer(CustomerGetInfoDto customer);

	CustomerInfoDto updateCustomerInfo(CustomerInfoDto customer);

	CustomerDto getCustomerById(Integer customerId);

	// Available
	List<CustomerDto> getAllCustomersActive();

	List<CustomerDto> findAllCustomers();

	void deleteCustomer(Integer customerId);
	
	AddressDto updateAddress(AddressDto addressDto);

}
