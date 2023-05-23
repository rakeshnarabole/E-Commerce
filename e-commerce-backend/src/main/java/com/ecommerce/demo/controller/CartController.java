package com.ecommerce.demo.controller;

import java.util.List;

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

import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.dto.CartItemDto;
import com.ecommerce.demo.payload.ApiResponse;
import com.ecommerce.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping(value = "/product/add")
	public ResponseEntity<?> addProductToCart(@RequestBody CartDto cartdto) {

		cartService.addProductToCart(cartdto);
		return new ResponseEntity<>(new ApiResponse("Product Added Successfully", true), HttpStatus.OK);
	}

	@GetMapping(value = "/getProductBy/{customerId}")
	public ResponseEntity<List<CartItemDto>> getCartProduct(@PathVariable("customerId") Integer customerId) {
		List<CartItemDto> cart = cartService.getCartProduct(customerId);
		return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "delete/productFrom/cart")
	public ResponseEntity<?> removeProductFromCart(@RequestBody CartDto cartdto) {

		cartService.removeProductFromCart(cartdto);
		return new ResponseEntity<>(new ApiResponse("Product removed Successfully", true), HttpStatus.OK);
	}

	@DeleteMapping(value = "/cart/clear/{customerId}")
	public ResponseEntity<?> clearCart(@PathVariable("customerId") Integer customerId) {

		cartService.clearCart(customerId);

		return new ResponseEntity<>(new ApiResponse("Cart cleared Successfully", true), HttpStatus.OK);
	}

}
