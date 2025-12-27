package com.api.rest.springboot.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.rest.springboot.ecommerce.entities.Product;
import com.api.rest.springboot.ecommerce.payload.ProductRequest;

public interface ProductService {

	public Product getProductById(Long productId);

	public List<Product> getAllProducts();

	public Product updateProduct(Long productId, ProductRequest request);

	public Product deleteProduct(Long productId);

	Product saveProduct(Product product, MultipartFile image) throws IOException;

	Product saveProduct(Product product, MultipartFile[] images) throws IOException;
}
