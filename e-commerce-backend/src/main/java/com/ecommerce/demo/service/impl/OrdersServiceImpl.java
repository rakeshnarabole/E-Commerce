package com.ecommerce.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.dto.OrderDto;
import com.ecommerce.demo.dto.OrdersDto;
import com.ecommerce.demo.exceptions.OrderException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.mapper.OrdersMapper;
import com.ecommerce.demo.models.Address;
import com.ecommerce.demo.models.CartItem;
import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.models.Orders;
import com.ecommerce.demo.repository.AddressRepository;
import com.ecommerce.demo.repository.CustomerRepository;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.repository.OrderRepository;
import com.ecommerce.demo.service.OrderService;

@Service
public class OrdersServiceImpl implements OrderService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private ExceptionLogRepository exceptionLogRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrdersMapper mapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private CartServiceImpl cartService;

	@Override
	public Orders updateOrder(OrdersDto ordersDto, Integer orderId) {

		return null;
	}

	@Override
	public OrdersDto getOrderById(Integer orderId) {

		Optional<Orders> opOrders = orderRepository.findById(orderId);
		Orders orders = new Orders();
		try {
			orders = opOrders.get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Orders", "orderId", orderId);
		}

		return mapper.mapToOrdersDto(orders);
	}

	@Override
	public List<OrdersDto> findAllOrders() {

		List<Orders> orders = orderRepository.findAll();

		return orders.stream().map(mapper::mapToOrdersDto).toList();
	}

	@Override
	public void deleteOrder(Integer orderId) {

		logger.info("delete order method initiated for OrderId{}", orderId);
		Optional<Orders> opOrder = this.orderRepository.findById(orderId);
		Orders order = new Orders();

		ExceptionLog exceptionLog = new ExceptionLog();

		try {
			order = opOrder.get();
			order.setIsArchive(true);
		} catch (Exception e) {
			exceptionLog.setAttribute(orderId);
			exceptionLog.setAttritubeType("orderId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Order not found with mention id:" + orderId);
			logger.error("Order not found with mention id:{}", orderId);
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);

			throw new ResourceNotFoundException("Order", "Orders", orderId);
		}
		this.orderRepository.save(order);
		logger.info("customer deleted succesfully");

	}

	@Override
	public Orders saveOrder(OrderDto odto) {

		Orders newOrder = new Orders();

		Customer customer = customerRepository.getById(odto.getCustomerId());

		Address address = addressRepository.getById(odto.getAddressId());

		newOrder.setCustomer(customer);
		List<CartItem> productsInCart = customer.getCustomerCart().getCartItems();
		List<CartItem> productsInOrder = new ArrayList<>(productsInCart);

		newOrder.setOrderCartItems(productsInOrder);
		newOrder.setTotalPrice(customer.getCustomerCart().getCartTotal());
		LocalDate newDate = LocalDate.now().plusDays(5);

		if (!productsInCart.isEmpty()) {

			newOrder.setAddress(address);
			newOrder.setOrderDate(LocalDate.now());
			newOrder.setDeliveryDate(newDate);
			newOrder.setOrderStatus("SUCCESS");
			newOrder.setPaymentType("Cash on Delevery");

			Double total = 0.0;

			List<CartItem> cartItemsList = customer.getCustomerCart().getCartItems();

			for (CartItem cartItem : cartItemsList) {
				Integer remainingQuantity = cartItem.getCartProduct().getStock() - cartItem.getCartItemQuantity();
				if (remainingQuantity < 0) {
					CartDto cartdto = new CartDto();
					cartdto.setProductId(cartItem.getCartProduct().getProductId());
					throw new OrderException("Product " + cartItem.getCartProduct().getName() + " OUT OF STOCK");
				}
				Double discountPrice = (productService.discountPrice(cartItem.getCartProduct()))
						* cartItem.getCartItemQuantity();

				total = total + discountPrice;
				cartItem.getCartProduct().setStock(remainingQuantity);

			}
			newOrder.setTotalPrice(total);

			cartService.clearCart(odto.getCustomerId());
			return orderRepository.save(newOrder);
		} else {
			throw new OrderException("No Products in Cart");
		}
	}

	@Override
	public List<OrdersDto> getOrderByCustomerId(Customer customer) {

		List<Orders> orders = orderRepository.findByCustomer(customer);

		if (orders.isEmpty()) {
			throw new OrderException("Customer Id has no orders present");
		}
		return orders.stream().map(mapper::mapToOrdersDto).toList();
	}

	/*
	 * public Orders cancelOrderByCustomerId(Integer orderId) throws OrderException
	 * {
	 * 
	 * Optional<Orders> opOrders = orderRepository.findById(orderId);
	 * 
	 * Orders order = new Orders(); try { order = opOrders.get();
	 * if(order.getOrderStatus() == "SUCCESS") { order.setOrderStatus("CANCELLED");
	 * List<CartItem> cartItemList = order.getOrderCartItems();
	 * 
	 * for(CartItem cartItem : cartItemList) { int addedQuantity =
	 * cartItem.getCartProduct().get } } } catch (Exception e) { throw new
	 * OrderException("Customer Id has no orders present"); }
	 * 
	 * return null; }
	 */

}
