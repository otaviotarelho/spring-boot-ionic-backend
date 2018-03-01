package com.otaviotarelho.curso.services;

import org.springframework.mail.SimpleMailMessage;

import com.otaviotarelho.curso.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
