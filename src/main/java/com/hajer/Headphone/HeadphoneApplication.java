package com.hajer.Headphone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HeadphoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeadphoneApplication.class, args);
	}

}
