package com.ecommerce.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.repository.ExceptionLogRepository;
import com.ecommerce.demo.service.ExceptionLogService;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

	@Autowired
	private ExceptionLogRepository exceptionLogRepository;

	@Override
	public List<ExceptionLog> getAllExceptionLogs() {

		return exceptionLogRepository.findAll();
	}

	@Override
	public List<ExceptionLog> getFirst5Logs() {

		return exceptionLogRepository.findFirst5ByOrderByLogId();
	}

	@Override
	public List<ExceptionLog> getLast5logs() {

		return exceptionLogRepository.findLast5ByOrderByLogIdDesc();
	}

}
