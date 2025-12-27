package com.api.rest.springboot.ecommerce.service;

import java.util.List;

import com.api.rest.springboot.ecommerce.entities.Category;

public interface CategoryService {

	Category saveCategory(Category category);

	List<Category> getAllCategories();

	Category getCategoryById(String id);

	Category updateCategory(String id, Category category);

	Category deleteCategory(String id);
}
