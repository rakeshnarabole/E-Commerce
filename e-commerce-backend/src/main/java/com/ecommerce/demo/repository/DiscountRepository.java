package com.ecommerce.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.models.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount , Integer>{
	
	Optional<Discount> findByDiscountIdAndIsArchive(int discountId, Boolean isArchive);

}
