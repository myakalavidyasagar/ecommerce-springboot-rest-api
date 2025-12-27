package com.api.rest.springboot.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.rest.springboot.ecommerce.entities.Product;
import com.api.rest.springboot.ecommerce.payload.ApiResponse;
import com.api.rest.springboot.ecommerce.payload.ProductRequest;
import com.api.rest.springboot.ecommerce.service.ProductService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/product")
public class Productcontroller {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> saveProduct(@RequestPart("product") String productJson,
			@RequestPart("images") MultipartFile[] images) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(productJson, Product.class);

		Product saved = productService.saveProduct(product, images);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse("Product Saved Successfully", saved, HttpStatus.CREATED));
	}

	// get product
	@GetMapping(value="/{productId}",produces = "application/json")
	public ResponseEntity<ApiResponse> getProduct(@PathVariable("productId") Long productId) {

		Product productById = productService.getProductById(productId);

		return ResponseEntity.ok(new ApiResponse("Product By Id", productById, HttpStatus.OK));
	}

	// get all products
	@GetMapping("/products")
	public ResponseEntity<ApiResponse> getAllProducts() {
		List<Product> allProducts = productService.getAllProducts();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All Products are :", allProducts, HttpStatus.OK),
				HttpStatus.OK);

	}

	// update Product
	@PutMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId,
			@RequestBody ProductRequest product) {
		Product updateProduct = productService.updateProduct(productId, product);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Product Updated Successfully :", updateProduct, HttpStatus.OK), HttpStatus.OK);

	}

	// delete Product
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") Long productId) {
		Product deletedProduct = productService.deleteProduct(productId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Product Deleted Successfully :", deletedProduct, HttpStatus.OK), HttpStatus.OK);

	}
}
