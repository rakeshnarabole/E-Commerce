package com.ecommerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
