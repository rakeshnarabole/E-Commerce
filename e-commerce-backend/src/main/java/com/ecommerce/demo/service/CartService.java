package com.ecommerce.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.dto.CartItemDto;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.models.Cart;

@Service
public interface CartService {

	public Cart addProductToCart(CartDto cart) throws ResourceNotFoundException;

	public List<CartItemDto> getCartProduct(int customerId);

	void removeProductFromCart(CartDto cartDto) throws ResourceNotFoundException;

	public Cart clearCart(int customerId);

}
