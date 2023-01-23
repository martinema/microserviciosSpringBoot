package com.moto.servicio;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MotoServicioApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MotoServicioApplication.class, args);
      SpringApplication app = new SpringApplication(MotoServicioApplication.class);
      app.setDefaultProperties(Collections
        .singletonMap("server.port", "8083"));
      app.run(args);
	}

}
