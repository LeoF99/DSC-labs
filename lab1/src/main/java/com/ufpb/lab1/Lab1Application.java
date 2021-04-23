package com.ufpb.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
public class Lab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}
	
	@GetMapping("/")
	private ResponseEntity<String> verificarSaude() {
		return new ResponseEntity<String>("Server is running!", HttpStatus.OK);
	}

}
