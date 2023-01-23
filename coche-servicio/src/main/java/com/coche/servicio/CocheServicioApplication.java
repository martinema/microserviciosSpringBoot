package com.coche.servicio;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocheServicioApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MotoServicioApplication.class, args);
		SpringApplication app = new SpringApplication(CocheServicioApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
		app.run(args);
	}
}