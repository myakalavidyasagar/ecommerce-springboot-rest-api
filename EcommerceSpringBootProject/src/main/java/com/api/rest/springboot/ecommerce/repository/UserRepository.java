package com.api.rest.springboot.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.springboot.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

}
