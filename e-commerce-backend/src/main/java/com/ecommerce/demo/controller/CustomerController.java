package com.ecommerce.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.AddressDto;
import com.ecommerce.demo.dto.CustomerDto;
import com.ecommerce.demo.dto.CustomerGetInfoDto;
import com.ecommerce.demo.dto.CustomerInfoDto;
import com.ecommerce.demo.payload.ApiResponse;
import com.ecommerce.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// get customers
	@GetMapping("/get/all")
	public ResponseEntity<List<CustomerDto>> getAllCustomer() {
		List<CustomerDto> customers = this.customerService.findAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);

	}

	// get available customers
	@GetMapping("/get/all/available")
	public ResponseEntity<List<CustomerDto>> getAvailableCustomer() {
		List<CustomerDto> customers = this.customerService.getAllCustomersActive();
		return new ResponseEntity<>(customers, HttpStatus.OK);

	}

	// get customers by Id
	@GetMapping("/getbyId/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Integer customerId) {
		return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
	}

	// update customer
	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomerInfoDto> updateCustomer(@RequestBody CustomerInfoDto customerInfoDto,
			@PathVariable("customerId") Integer customerId) {
		customerInfoDto.setCustomerId(customerId);
		CustomerInfoDto updatedCustomer = this.customerService.updateCustomerInfo(customerInfoDto);
		return ResponseEntity.ok(updatedCustomer);
	}

	// archive customers
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Integer customerId) {

		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(new ApiResponse("Customer deleted Successfully", true), HttpStatus.OK);

	}

	// create customers
	@PostMapping("/new/add")
	public ResponseEntity<CustomerGetInfoDto> createCustomer(@RequestBody CustomerGetInfoDto customerDto) {

		customerService.createCustomer(customerDto);
		return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/address/byId")
	public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto addressDto){
		customerService.updateAddress(addressDto);
		return new ResponseEntity<>(addressDto, HttpStatus.ACCEPTED);
	}

}
