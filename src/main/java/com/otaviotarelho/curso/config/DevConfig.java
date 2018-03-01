package com.otaviotarelho.curso.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.otaviotarelho.curso.services.DatabaseService;
import com.otaviotarelho.curso.services.EmailService;
import com.otaviotarelho.curso.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			 return false;
		}
		
		databaseService.instantiateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailSerice() {
		return new SmtpEmailService();
	}
}
