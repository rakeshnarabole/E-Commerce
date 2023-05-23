package com.ecommerce.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.models.Product;
import com.ecommerce.demo.payload.ApiResponse;
import com.ecommerce.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductsController {

	@Autowired
	private ProductService productService;

	// get all products
	@GetMapping("/get/all")
	public ResponseEntity<List<ProductDto>> getAllproducts() {

		return ResponseEntity.ok(this.productService.getListOfProducts());

	}

	// get product by Id
	@GetMapping("/getbyId/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") int productId) {
		return ResponseEntity.ok(this.productService.getProductById(productId));
	}

	// delete product by Id
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable("productId") int productId) {

		this.productService.deleteProduct(productId);
		return new ResponseEntity<>(new ApiResponse("Product deleted Successfully", true), HttpStatus.OK);

	}

	// create products
	@PostMapping("new/add")
	public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {

		Product product = productService.addProduct(productDto);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	
	// update product Id
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateDiscount(@PathVariable("productId") int productId, @RequestBody ProductDto productDto) {
		
		productDto.setProductId(productId);
		Product updatedProduct = productService.updateProduct(productDto);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	
}
