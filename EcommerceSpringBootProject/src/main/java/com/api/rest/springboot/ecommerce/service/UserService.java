package com.api.rest.springboot.ecommerce.service;

import java.util.List;

import com.api.rest.springboot.ecommerce.entities.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUsers();

	User getUserById(String id);

	User updateUser(String id, User user);

	User deleteUser(String id);
	
	User loginUser(String email,String password);
}
