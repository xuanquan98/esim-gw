package com.quanvx.esim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsimApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsimApplication.class, args);
	}

}
