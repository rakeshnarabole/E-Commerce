package com.ecommerce.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.models.ExceptionLog;
import com.ecommerce.demo.service.ExceptionLogService;

@RestController
@RequestMapping("api/log")
public class ExceptionLogController {

	@Autowired
	private ExceptionLogService exceptionLogService;

	// get all logs
	@GetMapping("/get/all")
	public ResponseEntity<List<ExceptionLog>> getAllExceptionLogs() {

		List<ExceptionLog> logs = this.exceptionLogService.getAllExceptionLogs();

		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	// get last five logs logs
	@GetMapping("/get/last5logs")
	public ResponseEntity<List<ExceptionLog>> getLast5ExceptionLogs() {

		List<ExceptionLog> logs = this.exceptionLogService.getLast5logs();

		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	// get first five logs logs
	@GetMapping("/get/first5logs")
	public ResponseEntity<List<ExceptionLog>> getFirst5ExceptionLogs() {

		List<ExceptionLog> logs = this.exceptionLogService.getFirst5Logs();

		return new ResponseEntity<>(logs, HttpStatus.OK);
	}


}
