package com.ecommerce.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.models.Customer;
import com.ecommerce.demo.models.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	  List<Orders> findByCustomer(Customer customer);

	
}
