package com.noi.Post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PostApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostApplication.class, args);
	}

}
