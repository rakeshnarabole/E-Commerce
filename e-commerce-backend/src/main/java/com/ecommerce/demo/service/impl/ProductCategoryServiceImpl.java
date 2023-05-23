package com.ecommerce.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.ProductCategoryDto;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.models.ProductCategory;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.repository.ProductCategoryRepository;
import com.ecommerce.demo.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

	@Autowired
	private ProductCategoryRepository categoryRepository;

	@Autowired
	private ExceptionLogRepository exceptionLogRepository;

	public List<ProductCategory> listCategories() {
		return categoryRepository.findAll();
	}

	public Optional<ProductCategory> getCategoryById(Integer categoryId) {
		
		Optional<ProductCategory> category =  categoryRepository.findById(categoryId);
		if(category.isEmpty()) {
			throw new ResourceNotFoundException("productCategory", "CategoryId", categoryId);
		}
		
		return category;

	}

	public ProductCategory updateCategory(ProductCategoryDto newCategory) {

		Optional<ProductCategory> opcategory = categoryRepository.findById(newCategory.getId());
		ProductCategory category = new ProductCategory();
		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("update Product Category initiated for categoryId:{}", newCategory.getId());

		try {
			category = opcategory.get();
			category.setName(newCategory.getName());
			category.setDescription(newCategory.getDescription());
			category.setName(newCategory.getName());

		} catch (Exception e) {
			exceptionLog.setAttribute(newCategory.getId());
			exceptionLog.setAttritubeType("CategoryId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Category not found with mention id:" + newCategory.getId());
			logger.error("Product category not found with mention id:{}", newCategory.getId());
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);

			throw new ResourceNotFoundException("ProductCategory", "CsategoryId", newCategory.getId());
		}
		logger.info("Product category updated successfully for categoryId:{}", newCategory.getId());
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		logger.info("delete category initiated for categoryId:{}", categoryId);

		Optional<ProductCategory> opCategory = this.categoryRepository.findById(categoryId);
		ProductCategory category = new ProductCategory();
		ExceptionLog exceptionLog = new ExceptionLog();

		try {
			category = opCategory.get();
			category.setIsArchive(true);
		} catch (Exception e) {

			exceptionLog.setAttribute(categoryId);
			exceptionLog.setAttritubeType("categoryId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Product category not found with mention id:" + categoryId);
			logger.error("Category not found with mention id:{}", categoryId);
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);

			throw new ResourceNotFoundException("ProductCategory", "categoryId", categoryId);
		}
		this.categoryRepository.save(category);
		logger.info("Category delete successfully for categoryId:{}", categoryId);

	}

	@Override
	public ProductCategory createCategory(ProductCategoryDto categoryDto) {

		ProductCategory productCategory = new ProductCategory(categoryDto.getId(), categoryDto.getName(),
				categoryDto.getDescription(), categoryDto.getIsArchive());

		return categoryRepository.save(productCategory);
	}

}
