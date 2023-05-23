package com.ecommerce.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.exceptions.ProductNotFoundException;
import com.ecommerce.demo.mapper.ProductMapper;
import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.models.Product;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.repository.ProductRepository;
import com.ecommerce.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ExceptionLogRepository exceptionLogRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper mapper;

	// list of all products
	@Override
	public List<ProductDto> getListOfProducts() {
		List<Product> products = productRepository.findAll();

		return products.stream().map(mapper::getDtoFromProduct).toList();

	}

	// add products
	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = mapper.getProductFromDto(productDto);

		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer productId) {

		Optional<Product> opProduct = this.productRepository.findById(productId);
		Product product = new Product();
		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("Prodcut deletion initiated");
		try {
			product = opProduct.get();
			product.setIsArchive(true);
		} catch (Exception e) {
			exceptionLog.setAttribute(productId);
			exceptionLog.setAttritubeType("ProductId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Product not found with mention id:" + productId);
			logger.error("Product not found with mention id:{}", productId);

			exceptionLogRepository.save(exceptionLog);
			throw  new ProductNotFoundException("Product Not found");
		}
		logger.info("Prodcut deletion successfully");
		this.productRepository.save(product);
	}

	@Override
	public ProductDto getProductById(Integer productId) {

		Product product = productRepository.findById(productId).get();

		return mapper.getDtoFromProduct(product);

	}

	@Override
	public Product updateProduct(ProductDto productDto) {

		Optional<Product> opProduct = productRepository.findById(productDto.getProductId());

		Product product = new Product();
		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("update Product  initiated ");

		try {
			product = opProduct.get();
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			product.setStock(productDto.getStock());

		} catch (Exception e) {
			exceptionLog.setAttribute(productDto.getProductId());
			exceptionLog.setAttritubeType("ProductId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Category not found with mention id:" + productDto.getProductId());
			logger.error("Product category not found with mention id:{}", productDto.getProductId());
			logger.warn("checkId");
			exceptionLogRepository.save(exceptionLog);

			throw new ProductNotFoundException("Product Not found");
		}
		logger.info("Product category updated successfully");
		return productRepository.save(product);
	}

	public double discountPrice(Product product) {
		Double discountPrice;
		Double discountPercentage;
		Double price;
		Double temp;

		discountPercentage = (double) product.getDiscount().getDiscountPercentage();
		price = product.getPrice();

		temp = 100 - discountPercentage;

		discountPrice = (temp * price) / 100;

		return discountPrice;
	}
}
