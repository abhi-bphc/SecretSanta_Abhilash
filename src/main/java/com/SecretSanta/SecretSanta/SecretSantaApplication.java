package com.SecretSanta.SecretSanta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.SecretSanta"})
public class SecretSantaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretSantaApplication.class, args);
	}

}
