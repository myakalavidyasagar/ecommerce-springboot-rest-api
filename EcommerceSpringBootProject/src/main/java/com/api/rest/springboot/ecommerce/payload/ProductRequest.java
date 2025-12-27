package com.api.rest.springboot.ecommerce.payload;

import lombok.Data;

/*@Data
public class ProductRequest {

	private String productTitle;
	private String productDesc;
	private double productPrice;
	private double productDiscount;
	private byte[] productPic;

	private String categoryId; // <--- IMPORTANT
}
*/
@Data
public class ProductRequest {
    private String productTitle;
    private String productDesc;
    private double productPrice;
    private double productDiscount;
    private String categoryId;
}
