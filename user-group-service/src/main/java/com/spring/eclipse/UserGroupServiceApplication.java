package com.spring.eclipse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class UserGroupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserGroupServiceApplication.class, args);
	}

}
