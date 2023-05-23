package com.ecommerce.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.AddressDto;
import com.ecommerce.demo.dto.CustomerDto;
import com.ecommerce.demo.dto.CustomerGetInfoDto;
import com.ecommerce.demo.dto.CustomerInfoDto;
import com.ecommerce.demo.exceptions.CustomException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.mapper.CustomerMapper;
import com.ecommerce.demo.models.Address;
import com.ecommerce.demo.models.Cart;
import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.repository.AddressRepository;
import com.ecommerce.demo.repository.CustomerRepository;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	/* Get actual class name to be printed on */
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ExceptionLogRepository exceptionLogRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Customer createCustomer(CustomerGetInfoDto customerDto) throws CustomException {
		Customer customer = new Customer();

		customer.setEmail(customerDto.getEmail());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setPhone(customerDto.getPhone());
		List<Address> addList = new ArrayList<>();
		for (Address address : customerDto.getAddress()) {
			address.setCustomer(customer);
			addList.add(address);
		}

		customer.setAddressList(addList);
		Cart cart = new Cart();
		cart.setCustomer(customer);
		cart.setCartTotal(0.0);
		customer.setCustomerCart(cart);

		return customerRepo.save(customer);
	}

	@Override
	public CustomerInfoDto updateCustomerInfo(CustomerInfoDto customerInfoDto) {

		Optional<Customer> opCustomer = customerRepo.findById(customerInfoDto.getCustomerId());
		Customer customer = new Customer();
		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("update customer initiated for customerId:{}", customerInfoDto.getCustomerId());

		try {
			customer = opCustomer.get();
			customer.setEmail(customerInfoDto.getEmail());
			customer.setFirstName(customerInfoDto.getFirstName());
			customer.setLastName(customerInfoDto.getLastName());
			customer.setPhone(customerInfoDto.getPhone());

		} catch (Exception e) {
			exceptionLog.setAttribute(customerInfoDto.getCustomerId());
			exceptionLog.setAttritubeType("CustomerId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Customer not found with mention id:" + customerInfoDto.getCustomerId());
			logger.error("Customer not found with mention id:{}", customerInfoDto.getCustomerId());
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);

			throw new ResourceNotFoundException("Customer", "customerId", customerInfoDto.getCustomerId());
		}
		logger.info("customer updated successfully for customerId:{}", customerInfoDto.getCustomerId());
		customerRepo.save(customer);
		return customerInfoDto;

	}

	@Override
	public CustomerDto getCustomerById(Integer customerId) {

		Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
		Customer customer = new Customer();

		try {
			customer = optionalCustomer.get();

		} catch (Exception e) {
			throw new ResourceNotFoundException("customer", "CustomerId", customerId);
		}
		return customerMapper.mapToCustomerDto(customer);

	}

	@Override
	public void deleteCustomer(Integer customerId) {

		logger.info("delete customer method initiated for customerId{}", customerId);
		Optional<Customer> opCustomer = this.customerRepo.findById(customerId);
		Customer customer = new Customer();

		ExceptionLog exceptionLog = new ExceptionLog();

		try {
			customer = opCustomer.get();
			customer.setIsArchive(true);
		} catch (Exception e) {
			exceptionLog.setAttribute(customerId);
			exceptionLog.setAttritubeType("CustomerId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Customer not found with mention id:" + customerId);
			logger.error("Customer not found with mention id:{}", customerId);
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);

			throw new ResourceNotFoundException("Customer", "customerId", customerId);
		}

		this.customerRepo.save(customer);
		logger.info("customer deleted succesfully");
	}

	@Override
	public List<CustomerDto> getAllCustomersActive() {
		List<Customer> customers = customerRepo.findAvailableCustomers();

		return customers.stream().map(customerMapper::mapToCustomerDto).toList();
	}

	@Override
	public List<CustomerDto> findAllCustomers() {
		List<Customer> customers = customerRepo.findAll();

		return customers.stream().map(customerMapper::mapToCustomerDto).toList();
	}

	@Override
	public AddressDto updateAddress(AddressDto addressDto) {
		try {
			Address customerAddress = addressRepository.getById(addressDto.getAddressId());

			customerAddress.setStreetNum(addressDto.getStreetNum());
			customerAddress.setCity(addressDto.getCity());
			customerAddress.setState(addressDto.getState());
			customerAddress.setCountry(addressDto.getCountry());
			customerAddress.setPincode(addressDto.getPincode());
			addressRepository.save(customerAddress);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Address", "AddressId", addressDto.getAddressId());
		}
		return addressDto;
	}

}
