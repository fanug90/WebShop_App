package com.webshop.domain;


public class Account {

	private final double accountNumber;
	private final String userName;
	private final String password;

	public Account(Double accountNumber, String userName, String password) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.password = password;
	}

	public double getAccountNumber() {
		return accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	

}
