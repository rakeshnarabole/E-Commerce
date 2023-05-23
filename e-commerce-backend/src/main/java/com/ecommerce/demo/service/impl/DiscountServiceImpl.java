package com.ecommerce.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.dto.DiscountDto;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.models.Discount;
import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.repository.DiscountRepository;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	ExceptionLogRepository exceptionLogRepository;

	@Override
	public List<Discount> getAllDiscount() {

		return discountRepository.findAll();
	}

	@Override
	public Discount createDiscount(DiscountDto discountDto) {

		Discount newDiscount = new Discount(discountDto.getDiscountId(), discountDto.getName(),
				discountDto.getDescription(), discountDto.getDiscountPercentage(), discountDto.getFestival(),
				discountDto.getFromDate(), discountDto.getToDate(), discountDto.getIsArchive());
		newDiscount.setIsArchive(false);

		return this.discountRepository.save(newDiscount);
	}

	@Override
	public void deleteDiscount(Integer discountId) {

		Optional<Discount> opDiscount = discountRepository.findById(discountId);
		Discount discount = new Discount();
		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("Discount deletion inited for discountId:{}", discountId);
		try {
			discount = opDiscount.get();
			discount.setIsArchive(true);
		} catch (Exception e) {

			exceptionLog.setAttribute(discountId);
			exceptionLog.setAttritubeType("DiscountId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Discount not found with mention id:" + discountId);
			logger.error("Discount not found with mention id:{}", discountId);
			logger.warn("checkDiscountId");

			exceptionLogRepository.save(exceptionLog);
			throw new ResourceNotFoundException("Discount", "discountId", discountId);
		}
		logger.info("Discount delete successfully discountId:{}", discountId); 

		this.discountRepository.save(discount);

	}

	@Override
	public Optional<Discount> getDiscountById(Integer discountId) {

		Optional<Discount> opDiscount = discountRepository.findById(discountId);
		
		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("Discount get data inited for discountId:{}", discountId);
		if(opDiscount.isEmpty()) {
			exceptionLog.setAttribute(discountId);
			exceptionLog.setAttritubeType("DiscountId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Discount not found with mentioned id:" +discountId);

			exceptionLogRepository.save(exceptionLog);
			throw new ResourceNotFoundException("Dicount", "dicountId", discountId);
		}
		return discountRepository.findByDiscountIdAndIsArchive(discountId, false);
	}

	@Override
	public Discount updateDiscount(DiscountDto newDiscount) {

		Discount existingDiscount = new Discount();

		ExceptionLog exceptionLog = new ExceptionLog();
		logger.info("Discount updatition initiated for discountId:{}", newDiscount.getDiscountId());

		try {
			existingDiscount = discountRepository.getById(newDiscount.getDiscountId());

			existingDiscount.setDescription(newDiscount.getDescription());
			existingDiscount.setName(newDiscount.getName());
			existingDiscount.setDiscountPercentage(newDiscount.getDiscountPercentage());
			existingDiscount.setFestival(newDiscount.getFestival());
			existingDiscount.setFromDate(newDiscount.getFromDate());
			existingDiscount.setToDate(newDiscount.getToDate());
		} catch (Exception e) {
			exceptionLog.setAttribute(newDiscount.getDiscountId());
			exceptionLog.setAttritubeType("DiscountId");
			exceptionLog.setCreatedDate(new Date());
			exceptionLog.setExceptionMessage("Discount not found with mention id:" + newDiscount.getDiscountId());
			logger.error("Discount not found with mention id:{}", newDiscount.getDiscountId());
			logger.warn("checkDiscountId");

			exceptionLogRepository.save(exceptionLog);

			throw new ResourceNotFoundException("Discount", "discountId", newDiscount.getDiscountId());
		}
		logger.info("Discount updated  successfullyfor discountId:{}", newDiscount.getDiscountId());
		return discountRepository.save(existingDiscount);

	}

}
