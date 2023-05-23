package com.ecommerce.demo.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.models.Discount;
import com.ecommerce.demo.models.Product;
import com.ecommerce.demo.models.ProductCategory;
import com.ecommerce.demo.service.DiscountService;
import com.ecommerce.demo.service.ProductCategoryService;

@Component
public class ProductMapper {

	@Autowired
	ProductCategoryService categoryService;

	@Autowired
	DiscountService discountService;

	public Product getProductFromDto(ProductDto productDto) {
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setStock(productDto.getStock());
		product.setIsArchive(productDto.getIsArchive());

		Optional<ProductCategory> opCategory = categoryService.getCategoryById(productDto.getCatagoryId());
		ProductCategory category = new ProductCategory();
		if (opCategory.isPresent()) {
			category = opCategory.get();
		}

		Optional<Discount> opDiscount = discountService.getDiscountById(productDto.getDiscountId());
		Discount discount = new Discount();
		if (opDiscount.isPresent()) {
			discount = opDiscount.get();
		}

		product.setProductCategory(category);
		product.setDiscount(discount);
		return product;

	}

	public ProductDto getDtoFromProduct(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.getProductId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setDescription(product.getDescription());
		productDto.setStock(product.getStock());
		productDto.setIsArchive(product.getIsArchive());
		productDto.setCatagoryId(product.getProductCategory().getCategoryId());
		productDto.setDiscountId(product.getDiscount().getDiscountId());
		

		Double discountPrice;
		Double discountPercentage;
		Double price;
		Double temp;

		discountPercentage = (double) product.getDiscount().getDiscountPercentage();
		price = product.getPrice();

		temp = 100 - discountPercentage;

		discountPrice = (temp * price) / 100;

		productDto.setDiscountPrice(discountPrice);

		return productDto;
	}

}
