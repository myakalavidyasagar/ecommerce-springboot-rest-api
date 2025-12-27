package com.api.rest.springboot.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

	private String message;
	private Object data;
}
