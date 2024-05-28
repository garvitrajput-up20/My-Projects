package com.garvitrajput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.garvitrajput")
public class LibSysFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibSysFrontendApplication.class, args);
	}

}
