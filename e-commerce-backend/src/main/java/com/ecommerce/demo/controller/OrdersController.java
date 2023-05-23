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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.OrderDto;
import com.ecommerce.demo.dto.OrdersDto;
import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.Orders;
import com.ecommerce.demo.payload.ApiResponse;
import com.ecommerce.demo.repository.CustomerRepository;
import com.ecommerce.demo.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerRepository customerRepository;

	// get all orders
	@GetMapping("/get/all")
	public ResponseEntity<List<OrdersDto>> getAllorders() {

		return ResponseEntity.ok(this.orderService.findAllOrders());
	}

	// get orders by Id
	@GetMapping("/getById/{orderId}")
	public ResponseEntity<OrdersDto> getOrderById(@PathVariable Integer orderId) {
		return ResponseEntity.ok(this.orderService.getOrderById(orderId));
	}
	
	
	// archive order by Id
	@DeleteMapping("/deleteByIds/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Integer orderId) {

		orderService.deleteOrder(orderId);
		return new ResponseEntity<>(new ApiResponse("Orders deleted Successfully", true), HttpStatus.OK);

	}

	@PostMapping("/place")
	public ResponseEntity<Orders> addTheNewOrder(@Valid @RequestBody OrderDto odto) {

		Orders savedOrder = orderService.saveOrder(odto);
		return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);

	}
	
	@GetMapping("/get/odersByCustomer/{customerId}")
	public ResponseEntity<List<OrdersDto>> getOrdersByCustomer(@PathVariable("customerId") Integer customerId) {

		Customer customer = customerRepository.getById(customerId); 
		
		return ResponseEntity.ok(this.orderService.getOrderByCustomerId(customer));
	}

}
