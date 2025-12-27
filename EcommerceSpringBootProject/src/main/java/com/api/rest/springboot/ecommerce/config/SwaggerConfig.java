package com.api.rest.springboot.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

	@Configuration
	public class SwaggerConfig {

	    @Bean
	    OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("AddToCartToOrder API Documentation")
	                        .version("1.0")
	                        .description("API documentation for CartToOrder application"));
	    }
	}

