package com.ecommerce.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.ecommerce.demo.dto.ProductCategoryDto;
import com.ecommerce.demo.models.ProductCategory;
import com.ecommerce.demo.payload.ApiResponse;
import com.ecommerce.demo.service.ProductCategoryService;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService categoryService;

	// get all categories
	@GetMapping("/get/all")
	public ResponseEntity<List<ProductCategory>> getAllCategories() {

		return ResponseEntity.ok(this.categoryService.listCategories());
	}

	// get categories by Id
	@GetMapping("/getById/{categoryId}")
	public ResponseEntity<Optional<ProductCategory>> getCategoryById(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}

	// create customers
	@PostMapping("/new/add")
	public ResponseEntity<ProductCategory> createCustomer(@RequestBody ProductCategoryDto categoryDto) {

		ProductCategory category = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	
	// archive category by Id
	@DeleteMapping("/deleteById/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId) {

		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Category deleted Successfully", true), HttpStatus.OK);

	}
	
	
	// update category by Id
	@PutMapping("/updateById/{categoryId}")
	public ResponseEntity<ProductCategory> updateDiscount(@PathVariable("categoryId") int categoryId, @RequestBody ProductCategoryDto categoryDto) {
		categoryDto.setId(categoryId);
		ProductCategory upDatedCategory = categoryService.updateCategory(categoryDto);
		return new ResponseEntity<>(upDatedCategory, HttpStatus.OK);
	}

}
