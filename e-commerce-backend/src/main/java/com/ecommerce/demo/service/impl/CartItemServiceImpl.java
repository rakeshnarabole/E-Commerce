package com.ecommerce.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.exceptions.ProductNotFoundException;
import com.ecommerce.demo.models.CartItem;
import com.ecommerce.demo.models.Product;
import com.ecommerce.demo.repository.ProductRepository;
import com.ecommerce.demo.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public CartItem createItemforCart(CartDto cartdto) {

		Product existingProduct = productRepository.findById(cartdto.getProductId())
				.orElseThrow(() -> new ProductNotFoundException("Product Not found"));

		if (existingProduct.getStock() == 0) {
			throw new ProductNotFoundException("Product OUT OF STOCK");
		}
		
		CartItem newItem = new CartItem();

		newItem.setCartItemQuantity(cartdto.getQuantity());

		newItem.setCartProduct(existingProduct);

		return newItem;
	}

	
}
