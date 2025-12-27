package com.api.rest.springboot.ecommerce.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.springboot.ecommerce.entities.User;
import com.api.rest.springboot.ecommerce.payload.ApiResponse;
import com.api.rest.springboot.ecommerce.payload.LoginRequest;
import com.api.rest.springboot.ecommerce.payload.LoginResponse;
import com.api.rest.springboot.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// save User
	@PostMapping("/save-user")
	ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		ApiResponse response = new ApiResponse("User Created Successfully !", saveUser, HttpStatus.CREATED);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);

	}

	// get user by id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") String id) {

		User user = userService.getUserById(id);
		ApiResponse response = new ApiResponse("User Details Are :", user, HttpStatus.OK);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	// get all users
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllUsers() {

		// ApiResponse response = new ApiResponse("All users are", allUsers,
		// HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("All users are", userService.getAllUsers(), HttpStatus.OK), HttpStatus.OK);
	}

	// delete user by id
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") String id) {
		User deleteUser = userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", deleteUser, HttpStatus.OK),
				HttpStatus.OK);
	}

	// update user by ID
	@PutMapping("update/{id}")
	public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		User updateUser = userService.updateUser(id, user);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Updated Successfully", updateUser, HttpStatus.OK),
				HttpStatus.OK);
	}
	
	//login User
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest request){
		User loginUser = userService.loginUser(request.getEmail(), request.getPassword());
		return new ResponseEntity<>(new LoginResponse("User Login Successfull", loginUser),HttpStatus.OK);
	}

}
