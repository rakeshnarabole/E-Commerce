package com.ecommerce.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.ProductCategoryDto;
import com.ecommerce.demo.models.ProductCategory;

@Service
public interface ProductCategoryService {
	
	public List<ProductCategory> listCategories();

	public ProductCategory createCategory(ProductCategoryDto category);
	
	void deleteCategory(Integer categoryId);
	
	public Optional<ProductCategory> getCategoryById(Integer categoryId);

	public ProductCategory updateCategory(ProductCategoryDto categoryDto);
		
}
