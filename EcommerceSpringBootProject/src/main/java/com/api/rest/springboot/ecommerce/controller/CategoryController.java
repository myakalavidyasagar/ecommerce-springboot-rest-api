package com.api.rest.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.springboot.ecommerce.entities.Category;
import com.api.rest.springboot.ecommerce.payload.ApiResponse;
import com.api.rest.springboot.ecommerce.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// save category
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		Category saveCategory = categoryService.saveCategory(category);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Category saved with this name :- " + category.getCatName(), saveCategory,
						HttpStatus.CREATED),
				HttpStatus.CREATED);

		/*
		 * return ResponseEntity .status(HttpStatus.CREATED) .body(new
		 * ApiResponse("Category Details are :", categoryById, HttpStatus.CREATED));
		 */

	}

	// get category
	@GetMapping("/{catId}")
	public ResponseEntity<ApiResponse> getCategory(@PathVariable("catId") String catId) {
		Category categoryById = categoryService.getCategoryById(catId);
		return ResponseEntity.ok(new ApiResponse("Category Details are :", categoryById, HttpStatus.OK));

	}

	// get all categories
	@GetMapping("/all-categories")
	public ResponseEntity<ApiResponse> getAllCategories() {
		/* categoryService.getAllCategories(); */
		return ResponseEntity.ok(new ApiResponse("All categories", categoryService.getAllCategories(), HttpStatus.OK));
	}

	// update category
	@PutMapping("/update-category/{catId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("catId") String catId,
			@RequestBody Category category) {
		Category updateCategory = categoryService.updateCategory(catId, category);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Category Updated successfully !", updateCategory, HttpStatus.OK), HttpStatus.OK);
	}

	@DeleteMapping("/delete-category/{catId}")
	public ResponseEntity<ApiResponse> deleteCat(@PathVariable("catId") String catId) {
		Category deleteCategory = categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Category Deleted successfully !", deleteCategory, HttpStatus.OK), HttpStatus.OK);
	}

}
