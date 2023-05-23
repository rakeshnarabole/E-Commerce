package com.ecommerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
