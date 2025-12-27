package com.api.rest.springboot.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rest.springboot.ecommerce.entities.User;
import com.api.rest.springboot.ecommerce.exception.ResourceNotFoundException;
import com.api.rest.springboot.ecommerce.repository.UserRepository;
import com.api.rest.springboot.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		user.setId(UUID.randomUUID().toString());
		User saveUser = userRepository.save(user);
		return saveUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> findAllUsers = userRepository.findAll();
		return findAllUsers;
	}

	@Override
	public User getUserById(String id) {

		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("This User Not Found ! with this ID :" + id));

	}

	@Override
	public User updateUser(String id, User user) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("This User Not Found ! with this ID :" + id));
		existingUser.setUsername(user.getUsername());
		existingUser.setEmail(user.getEmail());
		// existingUser.setPassword(user.getPassword());

		existingUser.setMobile(user.getMobile());
		existingUser.setGender(user.getGender());
		existingUser.setProfile(user.getProfile());

		if (user.getPassword() != null && !user.getPassword().isBlank()) {
			existingUser.setPassword(user.getPassword());
		}
		return userRepository.save(existingUser);
	}

	@Override
	public User deleteUser(String id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("This User Not Found ! with this ID :" + id));

		userRepository.delete(existingUser);
		return existingUser;

	}

	@Override
	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Invalid Email"));

		if (!user.getPassword().trim().equals(password.trim())) {
			throw new ResourceNotFoundException("Invalid Password Enterd or Wrong Password !");
		}

		return user;
	}

}
