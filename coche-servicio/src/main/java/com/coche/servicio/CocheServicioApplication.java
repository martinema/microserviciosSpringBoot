package com.coche.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocheServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocheServicioApplication.class, args);
//		SpringApplication app = new SpringApplication(CocheServicioApplication.class);
//		app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
//		app.run(args);
	}
}