package com.ecommerce.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ecommerce.demo.models.ExceptionLog;

@Service
public interface ExceptionLogService {
	
	public List<ExceptionLog> getFirst5Logs();
	
	public List<ExceptionLog> getLast5logs();

	public List<ExceptionLog> getAllExceptionLogs();
}
