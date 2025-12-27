package com.api.rest.springboot.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@NotBlank(message="username is required")
	@Size(min=3, max=20,message="username must be 3-20 characters only")
	private String username;
	
	@NotBlank(message="email is rquired")
	@Email(message="invalid email farmat")
	@Column(unique=true)
	private String email;
	
	@NotBlank(message="password is required")
	@Size(min=6,max=20, message="password must be minimum 6 characters and maximum 15 characters")
	private String password;
	
	@NotNull(message="mobile number is required")
	@Digits(integer=10,fraction = 0,message="mobile number must be 10 digits")
	private Long mobile;
	
	@Lob
	private String profile;
	
	@NotBlank(message="gender is required")
	@Pattern(regexp = "Male|Female|Other",message="Gender Must be Male,Female or Other")
	private String gender;

}
