package com.ecommerce.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.models.ExceptionLog;

@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Integer> {
	
	
	 List<ExceptionLog> findLast5ByOrderByLogIdDesc();
	 
	 List<ExceptionLog> findFirst5ByOrderByLogId();
}
