package com.api.rest.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.springboot.ecommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
