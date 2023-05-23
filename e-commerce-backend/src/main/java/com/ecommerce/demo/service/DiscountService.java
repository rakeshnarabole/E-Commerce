package com.ecommerce.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.DiscountDto;
import com.ecommerce.demo.models.Discount;

@Service
public interface DiscountService {
	
	public List<Discount> getAllDiscount();

	public Discount createDiscount(DiscountDto category);
	
	void deleteDiscount(Integer discoutId);
	
	public Optional<Discount> getDiscountById(Integer discoutId);

	public Discount updateDiscount(DiscountDto newDiscount);

	

}
