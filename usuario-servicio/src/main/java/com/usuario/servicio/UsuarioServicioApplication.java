package com.usuario.servicio;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuarioServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServicioApplication.class, args);
//        SpringApplication app = new SpringApplication(UsuarioServicioApplication.class);
//        app.setDefaultProperties(Collections
//          .singletonMap("server.port", "8081"));
//        app.run(args);
	}

}
