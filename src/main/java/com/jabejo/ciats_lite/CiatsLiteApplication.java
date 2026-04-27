package com.jabejo.ciats_lite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CiatsLiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiatsLiteApplication.class, args);
	}

}
