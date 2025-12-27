package com.api.rest.springboot.ecommerce.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.api.rest.springboot.ecommerce.entities.Category;
import com.api.rest.springboot.ecommerce.entities.Product;
import com.api.rest.springboot.ecommerce.entities.ProductImages;
import com.api.rest.springboot.ecommerce.exception.ResourceNotFoundException;
import com.api.rest.springboot.ecommerce.payload.ProductRequest;
import com.api.rest.springboot.ecommerce.repository.CategoryRepository;
import com.api.rest.springboot.ecommerce.repository.ProductRepository;
import com.api.rest.springboot.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

//	@Override
//	public Product saveProduct(Product product, MultipartFile[] images) throws IOException {
//
//		// save product first
//		Product savedProduct = productRepository.save(product);
//
//		// 1. Validate category
//		String catId = product.getCategory().getCatId();
//
//		Category category = categoryRepository.findById(catId)
//				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
//
//		product.setCategory(category);
//
//		// 2. Set added date
//		product.setAddedDate(new java.sql.Date(System.currentTimeMillis()));
//
//		// 4. Final price
//		double finalPrice = product.getProductPrice()
//				- (product.getProductPrice() * product.getProductDiscount() / 100);
//
//		product.setTotalPrice(finalPrice);
//
//		// 3. Save image
//		String uploadDir = "uploads/products/";
//		File dir = new File(uploadDir);
//		if (!dir.exists())
//			dir.mkdirs();
//
//		for (MultipartFile image : images) {
//			String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
//
//			String fileName = UUID.randomUUID() + ext;
//
//			// save file in folder
//			Files.copy(image.getInputStream(), Paths.get(uploadDir + fileName), StandardCopyOption.REPLACE_EXISTING);
//
//			// save image info into db
//			ProductImages pi = new ProductImages();
//			pi.setImageName(fileName);
//			// pi.setProduct(savedProduct);
//			pi.setProduct(product);
//			savedProduct.getImages().add(pi);
//			product.setImageNames(fileName);
//		}
//
//		// 5. Save to DB
//		return productRepository.save(savedProduct);
//	}
	
	
	@Override
	@Transactional
	public Product saveProduct(Product product, MultipartFile[] images) throws IOException {

	    // 1. Validate category
	    Category category = categoryRepository.findById(product.getCategory().getCatId())
	            .orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
	    product.setCategory(category);

	    // 2. Set added date
	    product.setAddedDate(new java.sql.Date(System.currentTimeMillis()));

	    // 3. Final price
	    double finalPrice = product.getProductPrice()
	            - (product.getProductPrice() * product.getProductDiscount() / 100);
	    product.setTotalPrice(finalPrice);

	    // 4. Save product to get ID
	    Product savedProduct = productRepository.save(product);

	    // 5. File upload folder
	    String uploadDir = "uploads/products/";
	    File dir = new File(uploadDir);
	    if (!dir.exists()) dir.mkdirs();

	    List<String> imageNamesList = new ArrayList<>();

	    // 6. Upload images and link
	    for (MultipartFile img : images) {

	        String ext = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
	        String fileName = UUID.randomUUID() + ext;

	        Files.copy(img.getInputStream(), Paths.get(uploadDir + fileName),
	                StandardCopyOption.REPLACE_EXISTING);

	        // save image table entry
	        ProductImages pi = new ProductImages();
	        pi.setImageName(fileName);
	        pi.setProduct(savedProduct);

	        savedProduct.getImages().add(pi);
	        imageNamesList.add(fileName);
	    }

	    // 7. Store as comma-separated string (optional)
	    savedProduct.setImageNames(String.join(",", imageNamesList));

	    // 8. Final save
	    return productRepository.save(savedProduct);
	}


	@Override
	public Product getProductById(Long productId) {
		Product findByIdProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found this id " + productId));
		return findByIdProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> findAllProducts = productRepository.findAll();
		return findAllProducts;
	}

	@Override
	public Product updateProduct(Long productId, ProductRequest request) {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found this id " + productId));
		existingProduct.setProductTitle(request.getProductTitle());
		existingProduct.setProductDesc(request.getProductDesc());
		existingProduct.setProductPrice(request.getProductPrice());
		existingProduct.setProductDiscount(request.getProductDiscount());
		// existingProduct.setProductPic(request.getProductPic());
		/*
		 * existingProduct.setAddedDate(request.getAddedDate());
		 * existingProduct.setCategory(request.getCategory());
		 */
		if (request.getCategoryId() != null) {
			Category category = categoryRepository.findById(request.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("category not found"));

			existingProduct.setCategory(category);
		}
		existingProduct.setAddedDate(new java.sql.Date(System.currentTimeMillis()));
		Product updatedProduct = productRepository.save(existingProduct);

		return updatedProduct;
	}

	@Override
	public Product deleteProduct(Long productId) {
		Product getProductById = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found this id " + productId));
		productRepository.delete(getProductById);
		return getProductById;
	}

	@Override
	public Product saveProduct(Product product, MultipartFile image) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
