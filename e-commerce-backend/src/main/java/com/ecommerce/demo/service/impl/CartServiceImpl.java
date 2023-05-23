package com.ecommerce.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.dto.CartItemDto;
import com.ecommerce.demo.exceptions.CartItemNotFound;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.models.Cart;
import com.ecommerce.demo.models.CartItem;
import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.repository.CartRepository;
import com.ecommerce.demo.repository.CustomerRepository;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.service.CartItemService;
import com.ecommerce.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ExceptionLogRepository exceptionLogRepository;

	@Override
	public Cart addProductToCart(CartDto cartDto) {

		Optional<Customer> opt = customerRepository.findById(cartDto.getCustomerId());

		Customer existingCustomer = new Customer();
		ExceptionLog exceptionLog = new ExceptionLog();

		try {
			existingCustomer = opt.get();
		} catch (Exception e) {

			exceptionLog.setAttribute(cartDto.getCustomerId());
			exceptionLog.setAttritubeType("customerId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Customer not found with mention id:" + cartDto.getCustomerId());
			logger.error("Customer not found with mention id:{}", cartDto.getCustomerId());
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);
			throw new ResourceNotFoundException("Customer", "CustomerId", cartDto.getCustomerId());

		}

		Cart customerCart = existingCustomer.getCustomerCart();

		List<CartItem> cartItems = customerCart.getCartItems();

		CartItem item = cartItemService.createItemforCart(cartDto);

		if (cartItems.isEmpty()) {
			cartItems.add(item);
			customerCart.setCartTotal((item.getCartProduct().getPrice()) * item.getCartItemQuantity());
		} else {
			boolean flag = false;
			for (CartItem c : cartItems) {
				if (c.getCartProduct().getProductId() == cartDto.getProductId()) {
					c.setCartItemQuantity(c.getCartItemQuantity() + cartDto.getQuantity());
					customerCart.setCartTotal((c.getCartProduct().getPrice() * c.getCartItemQuantity()));
					flag = true;
				}
			}
			if (!flag) {
				cartItems.add(item);
				customerCart.setCartTotal(
						customerCart.getCartTotal() + (item.getCartProduct().getPrice()) * item.getCartItemQuantity());
			}
		}

		return cartRepository.save(existingCustomer.getCustomerCart());

	}

	@Override
	public List<CartItemDto> getCartProduct(int customerId) {

		Optional<Customer> opt = customerRepository.findById(customerId);
		ExceptionLog exceptionLog = new ExceptionLog();
		List<CartItemDto> cartItemDtolist = new ArrayList<>();
		try {
			Customer existingCustomer = opt.get();

			Cart customerCart = existingCustomer.getCustomerCart();

			List<CartItem> cartItems = customerCart.getCartItems();

			for (CartItem ci : cartItems) {
				CartItemDto dto = new CartItemDto();
				dto.setCartItemId(ci.getCartItemId());
				dto.setCartProductId(ci.getCartProduct().getProductId());
				dto.setCartProductName(ci.getCartProduct().getName());
				dto.setCartItemQuantity(ci.getCartItemQuantity());

				cartItemDtolist.add(dto);
			}
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

		return cartItemDtolist;

	}

	@Override
	public void removeProductFromCart(CartDto cartDto) {

		Optional<Customer> opt = customerRepository.findById(cartDto.getCustomerId());

		if (opt.isEmpty())
			throw new ResourceNotFoundException("Customer", "CustomerId", cartDto.getCustomerId());

		Customer existingCustomer = opt.get();

		Cart customerCart = existingCustomer.getCustomerCart();

		List<CartItem> cartItems = customerCart.getCartItems();

		if (cartItems.isEmpty()) {
			throw new CartItemNotFound("Cart is empty");
		}

		boolean flag = false;

		for (CartItem c : cartItems) {
			System.out.println("Item" + c.getCartProduct());
			if (c.getCartProduct().getProductId() == cartDto.getProductId()) {
				c.setCartItemQuantity(c.getCartItemQuantity() - 1);

				customerCart.setCartTotal(customerCart.getCartTotal() - c.getCartProduct().getPrice());
				if (c.getCartItemQuantity() == 0) {

					cartItems.remove(c);

					cartRepository.save(customerCart);
				}
				flag = true;
			}
		}

		if (!flag) {
			throw new CartItemNotFound("Product not found to cart");
		}

		if (cartItems.isEmpty()) {
			cartRepository.save(customerCart);
			throw new CartItemNotFound("Cart is empty now");
		}

		cartRepository.save(customerCart);
	}

	// Method to clear entire cart
	@Override
	public Cart clearCart(int customerId) {

		Optional<Customer> opt = customerRepository.findById(customerId);

		if (opt.isEmpty())
			throw new ResourceNotFoundException("Customer", "customerId", customerId);

		Customer existingCustomer = opt.get();

		Cart customerCart = existingCustomer.getCustomerCart();

		if (customerCart.getCartItems().isEmpty()) {
			throw new CartItemNotFound("Cart is already empty");
		}

		List<CartItem> emptyCart = new ArrayList<>();

		customerCart.setCartItems(emptyCart);

		customerCart.setCartTotal(0.0);

		return cartRepository.save(customerCart);
	}

}
