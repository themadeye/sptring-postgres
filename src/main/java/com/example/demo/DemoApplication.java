package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// resource/static : where the web resource live
		// resource/template : where the web template live
		// resource/application.properties : where properties live
		SpringApplication.run(DemoApplication.class, args);
	}

}
