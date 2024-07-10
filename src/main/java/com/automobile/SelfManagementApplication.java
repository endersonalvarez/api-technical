package com.automobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SelfManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfManagementApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "PUT", "POST", "HEAD", "OPTIONS", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(false)
						.maxAge(3600);
				WebMvcConfigurer.super.addCorsMappings(registry);

			}
		};
	}
}
