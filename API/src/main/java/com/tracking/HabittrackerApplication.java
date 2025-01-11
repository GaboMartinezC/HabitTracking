package com.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.tracking.DAO")
@SpringBootApplication
public class HabittrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabittrackerApplication.class, args);
	}

}
