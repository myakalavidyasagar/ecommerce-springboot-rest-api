package com.api.rest.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.springboot.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
