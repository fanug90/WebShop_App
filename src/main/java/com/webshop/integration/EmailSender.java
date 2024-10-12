package com.webshop.integration;

import org.springframework.stereotype.Component;

import com.webshop.domain.Order;

@Component
public class EmailSender {

	public void sendOrderConfirmation(String message, String email) {
		System.out.println("send this " + message + "msg" + "to this" + email );
		
	}

}
