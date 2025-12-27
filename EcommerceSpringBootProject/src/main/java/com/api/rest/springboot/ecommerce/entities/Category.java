package com.api.rest.springboot.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="categories")
public class Category {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String catId;
	private String catName;
	private String catDesc;
}
