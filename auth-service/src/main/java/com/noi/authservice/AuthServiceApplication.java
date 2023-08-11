package com.noi.authservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.noi.authservice.entity.User;
import com.noi.authservice.service.AuthService;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(AuthService service) {
		return args ->{
			service.saveUser(new User(null,"peppino@pepp.pep", "1234"));			
		};
	}

}
