package com.api.rest.springboot.ecommerce.payload;

import lombok.Data;

@Data
public class LoginRequest {

	private String email;
	private String password;
}
