package com.otaviotarelho.curso.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.otaviotarelho.curso.services.DatabaseService;

@Configuration
@Profile("teste")
public class TestConfig {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		databaseService.instantiateDatabase();
		return true;
	}
}
