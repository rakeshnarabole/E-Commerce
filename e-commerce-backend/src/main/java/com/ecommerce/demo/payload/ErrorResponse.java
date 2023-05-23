package com.ecommerce.demo.payload;

public class ErrorResponse {

	private String errorMessage;
	private String errorApi;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorApi;
	}

	public void setErrorCode(String errorApi) {
		this.errorApi = errorApi;
	}

	public ErrorResponse(String errorMessage, String errorApi) {
		super();
		this.errorMessage = errorMessage;
		this.errorApi = errorApi;
	}

	public ErrorResponse() {
		super();
		
	}

}
