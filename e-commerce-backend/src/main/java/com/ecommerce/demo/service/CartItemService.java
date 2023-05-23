package com.ecommerce.demo.service;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.models.CartItem;

@Service
public interface CartItemService {
	
	public CartItem createItemforCart(CartDto cartdto);

}
