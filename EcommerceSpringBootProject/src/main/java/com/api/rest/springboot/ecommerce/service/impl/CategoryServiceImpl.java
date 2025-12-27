package com.api.rest.springboot.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rest.springboot.ecommerce.entities.Category;
import com.api.rest.springboot.ecommerce.exception.ResourceNotFoundException;
import com.api.rest.springboot.ecommerce.repository.CategoryRepository;
import com.api.rest.springboot.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category saveCategory(Category category) {
		category.setCatId(UUID.randomUUID().toString());
		Category saveCategory = categoryRepository.save(category);
		return saveCategory;
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> findAllCategories = categoryRepository.findAll();
		return findAllCategories;
	}

	@Override
	public Category getCategoryById(String id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with this id :" + id));
		return category;
	}

	@Override
	public Category updateCategory(String id, Category category) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with this id :" + id));
		existingCategory.setCatName(category.getCatName());
		existingCategory.setCatDesc(category.getCatDesc());
		Category updateCategory = categoryRepository.save(existingCategory);
		return updateCategory;
	}

	@Override
	public Category deleteCategory(String id) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with this id :" + id));
		categoryRepository.delete(existingCategory);
		return existingCategory;
	}

}
