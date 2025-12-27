package com.api.rest.springboot.ecommerce.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	@NotBlank(message = "Product title is required")
	@Size(min = 5, max = 100, message = "Title must be between 5 to 100 characters")
	private String productTitle;

	@NotBlank(message = "Product description is required")
	@Size(min = 10, message = "Description should be minimum 10 characters")
	private String productDesc;

	@Positive(message = "Price must be greater than 0")
	private double productPrice;

	@Min(value = 0, message = "Discount must be >= 0")
	@Max(value = 90, message = "Discount cannot exceed 90%")
	private double productDiscount;

	// IMAGE (Option 1: Base64 String stored in DB)
	/*
	 * @Lob
	 * 
	 * @Column(columnDefinition = "LONGBLOB") private byte[] productPic;
	 */
	private String imageNames;

//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<ProductImages> images = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductImages> images = new ArrayList<>();


	private Date addedDate;

	private double totalPrice;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Category is required")
	private Category category;
}
