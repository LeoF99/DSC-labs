package com.ufpb.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.ufpb.lab3.filtros.FiltroToken;

@SpringBootApplication
@RestController
public class Lab3Application {
	@Bean
	public FilterRegistrationBean<FiltroToken> filterJwt() {
		FilterRegistrationBean<FiltroToken> filterRB = new FilterRegistrationBean<FiltroToken>();
		filterRB.setFilter(new FiltroToken());
		filterRB.addUrlPatterns("/usuarios/auth/*", "/comentarios/auth/*");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab3Application.class, args);
	}
	
	@GetMapping("/")
	private ResponseEntity<String> verificarSaude() {
		return new ResponseEntity<String>("Server is running!", HttpStatus.OK);
	}

}
