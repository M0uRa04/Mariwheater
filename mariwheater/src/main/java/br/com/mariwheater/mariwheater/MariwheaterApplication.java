package br.com.mariwheater.mariwheater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MariwheaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MariwheaterApplication.class, args);
	}

}
