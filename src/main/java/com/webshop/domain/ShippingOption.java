package com.webshop.domain;

public class ShippingOption {

	private final String name;
	private final double price;
	private final String shipper;

	public ShippingOption(String name, Double price, String shipper) {
		super();
		this.name = name;
		this.price = price;
		this.shipper = shipper;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getShipper() {
		return shipper;
	}

}
