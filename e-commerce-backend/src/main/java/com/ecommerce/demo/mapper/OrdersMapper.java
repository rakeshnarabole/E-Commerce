package com.ecommerce.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.demo.dto.CartItemDto;
import com.ecommerce.demo.dto.OrdersDto;
import com.ecommerce.demo.models.CartItem;
import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.Orders;
import com.ecommerce.demo.repository.CustomerRepository;

@Component
public class OrdersMapper {

	@Autowired
	private CustomerRepository customerRepository;

	public Orders mapToOrders(OrdersDto ordersDto) {

		Customer customer = customerRepository.getById(ordersDto.getCustomerId());

		Orders order = new Orders();

		order.setCustomer(customer);
		order.setOrderId(ordersDto.getOrderId());
		order.setDeliveryDate(ordersDto.getDeliveryDate());
		order.setOrderDate(ordersDto.getOrderDate());
		order.setOrderStatus(ordersDto.getOrderStatus());
		order.setIsArchive(ordersDto.getIsArchive());
		order.setTotalPrice(ordersDto.getTotalPrice());

		return order;

	}

	public OrdersDto mapToOrdersDto(Orders orders) {

		OrdersDto orderDto = new OrdersDto();

		orderDto.setOrderId(orders.getOrderId());
		orderDto.setCustomerId(orders.getCustomer().getCustomerId());
		orderDto.setDeliveryDate(orders.getDeliveryDate());
		orderDto.setOrderDate(orders.getOrderDate());
		orderDto.setOrderStatus(orders.getOrderStatus());
		orderDto.setIsArchive(orders.getIsArchive());
		orderDto.setTotalPrice(orders.getTotalPrice());

		List<CartItemDto> cartItemDtolist = new ArrayList<>();
		List<CartItem> cartItems = orders.getOrdercartItems();

		for (CartItem ci : cartItems) {
			CartItemDto dto = new CartItemDto();
			dto.setCartItemId(ci.getCartItemId());
			dto.setCartProductId(ci.getCartProduct().getProductId());
			dto.setCartProductName(ci.getCartProduct().getName());
			dto.setCartItemQuantity(ci.getCartItemQuantity());

			cartItemDtolist.add(dto);
		}
		orderDto.setOrderCartItems(cartItemDtolist);

		return orderDto;
	}
}
