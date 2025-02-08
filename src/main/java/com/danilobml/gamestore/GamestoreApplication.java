package com.danilobml.gamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.danilobml.gamestore.security.config.SecurityConstants;

@SpringBootApplication
public class GamestoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GamestoreApplication.class, args);

		SecurityConstants.initialize(context.getEnvironment());
	}

}
