package com.am.appreview;

import org.apache.velocity.app.VelocityEngine;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppReviewApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppReviewApplication.class, args);
		initVelocityEngine();
	}

	private static void initVelocityEngine() {

	}

}
