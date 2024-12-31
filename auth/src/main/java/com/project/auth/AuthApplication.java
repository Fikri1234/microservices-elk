package com.project.auth;

import com.project.commons.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@EnableConfigurationProperties(AppJwtProperties.class)
@SpringBootApplication
@Import(GlobalExceptionHandler.class)
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
