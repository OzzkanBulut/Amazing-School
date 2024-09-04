package com.ozkanbulut.amazingschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ozkanbulut.amazingschool.repository")
@EntityScan("com.ozkanbulut.amazingschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AmazingschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazingschoolApplication.class, args);
	}

}
