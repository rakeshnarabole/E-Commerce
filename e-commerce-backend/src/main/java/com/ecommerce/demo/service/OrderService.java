package com.ecommerce.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.OrderDto;
import com.ecommerce.demo.dto.OrdersDto;
import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.Orders;

@Service
public interface OrderService {

	
	public Orders saveOrder(OrderDto odto);

	Orders updateOrder(OrdersDto ordersDto, Integer orderId);

	OrdersDto getOrderById(Integer orderId);
	
	List<OrdersDto> getOrderByCustomerId(Customer customer);

	List<OrdersDto> findAllOrders();

	void deleteOrder(Integer orderId);

}
