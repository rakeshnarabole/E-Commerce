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

import com.ecommerce.demo.dto.DiscountDto;
import com.ecommerce.demo.models.Discount;
import com.ecommerce.demo.payload.ApiResponse;
import com.ecommerce.demo.service.DiscountService;

@RestController
@RequestMapping("api/discount")
public class DiscountController {

	@Autowired
	private DiscountService discountService;

	// get all discount
	@GetMapping("/get/all")
	public ResponseEntity<List<Discount>> getAllDiscounts() {

		return ResponseEntity.ok(this.discountService.getAllDiscount());
	}

	// get discount by Id
	@GetMapping("/getById/{discountId}")
	public ResponseEntity<Optional<Discount>> getDiscountById(@PathVariable("discountId") Integer discountId) {
		return ResponseEntity.ok(this.discountService.getDiscountById(discountId));
	}

	// create Discount
	@PostMapping("/new/add")
	public ResponseEntity<Discount> createDiscount(@RequestBody DiscountDto discountDto) {

		Discount newDiscount = this.discountService.createDiscount(discountDto);

		return new ResponseEntity<>(newDiscount, HttpStatus.CREATED);
	}

	//update discount
	@PutMapping("/updateById/{discountId}")
	public ResponseEntity<Discount> updateDiscount(@PathVariable("discountId") int discountId, @RequestBody DiscountDto discount) {
		discount.setDiscountId(discountId);
		Discount updatedDiscount = discountService.updateDiscount(discount);
		return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
	}

	// archive discount by Id
	@DeleteMapping("/deleteById/{discountId}")
	public ResponseEntity<?> deleteDiscount(@PathVariable("discountId") Integer discountId) {

		discountService.deleteDiscount(discountId);
		return new ResponseEntity<>(new ApiResponse("Discount deleted Successfully", true), HttpStatus.OK);

	}

}
