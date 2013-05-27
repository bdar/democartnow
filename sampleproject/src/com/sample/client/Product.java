package com.sample.client;

public class Product {
	
	/*
	 * Class is not really required added for safty for GAE
	 */
	
	private String productName;
	private String productDescription;
	private String productPrice;
	private String productQuant;
	private String productSelect;
	
	public Product(String productName, String productDescription, String productPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	

	public String getProductQuant() {
		return productQuant;
	}

	public void setProductQuant(String productQuant) {
		this.productQuant = productQuant;
	}

	public String getProductSelect() {
		return productSelect;
	}

	public void setProductSelect(String productSelect) {
		this.productSelect = productSelect;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productDescription="  + productDescription + ", productPrice=" + productPrice + ", productQuant=" + productQuant + ", productSelect=" + productSelect + "]";
	}

}
