package com.ecommerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.models.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory , Integer> {
	

}
