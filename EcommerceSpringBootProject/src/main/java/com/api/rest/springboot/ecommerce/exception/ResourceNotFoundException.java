package com.api.rest.springboot.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {

		super(message);
	}
}
