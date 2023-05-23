package com.ecommerce.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.models.Product;

@Service
public interface ProductService {

	public List<ProductDto> getListOfProducts();

	// add products
	public Product addProduct(ProductDto productDto);
	
	//get by id
	ProductDto getProductById(Integer productId);

	// update products
	public Product updateProduct( ProductDto productDto);
	
	public void deleteProduct(Integer productId);

}
