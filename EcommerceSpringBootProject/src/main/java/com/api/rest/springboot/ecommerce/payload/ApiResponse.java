package com.api.rest.springboot.ecommerce.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ApiResponse {

	private String message;
	private Object data;
	private HttpStatus httpStatus;
	public ApiResponse(String message) {
		super();
		this.message = message;
	}
	public ApiResponse(String message, Object data, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.data = data;
		this.httpStatus = httpStatus;
	}
	public ApiResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	/*
	 * public ApiResponse(String message, Object data) { super(); this.message =
	 * message; this.data = data; }
	 */

	
	
	

}
