package com.product.ProductWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductWebAppApplication.class, args);
	}

}
