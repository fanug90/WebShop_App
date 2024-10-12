package com.webshop.domain;

import lombok.Getter;

@Getter
public class CreditCard {

	private final String number;
	private final String validationDate;

	public CreditCard(String number, String validationDate) {
		this.number = number;
		this.validationDate = validationDate;
	}

	public String getNumber() {
		return number;
	}

	public String getValidationDate() {
		return validationDate;
	}

}
