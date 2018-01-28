package com.daycare_manager.daycare_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DaycareManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaycareManagerApplication.class, args);
	}

}
