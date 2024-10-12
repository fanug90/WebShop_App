package com.webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Product {

	@Id
	private String productNumber;
	private String description;
	private double price;

	public Product() {
		super();
	}

	public Product(String productNumber, String description, double price) {
		super();
		this.productNumber = productNumber;
		this.description = description;
		this.price = price;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
