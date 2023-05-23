package com.ecommerce.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.demo.payload.ErrorResponse;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(ResourceNotFoundException exception, WebRequest wr) {
		ErrorResponse err = new ErrorResponse(exception.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorResponse> orderExceptionHandler(OrderException oe, WebRequest wr) {
		ErrorResponse err = new ErrorResponse(oe.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> productNotFound(ProductNotFoundException pnf, WebRequest wr) {
		ErrorResponse err = new ErrorResponse(pnf.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CartItemNotFound.class)
	public ResponseEntity<ErrorResponse> cartNotFound(CartItemNotFound pnf, WebRequest wr) {
		ErrorResponse err = new ErrorResponse(pnf.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
}
