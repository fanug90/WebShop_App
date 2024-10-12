package com.webshop.domain;

import lombok.Getter;

@Getter
public class OrderLine {
	private final Product product;
	private final int quantity;

	public OrderLine(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

}
