package com.usuario.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class UsuarioServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServicioApplication.class, args);
//        SpringApplication app = new SpringApplication(UsuarioServicioApplication.class);
//        app.setDefaultProperties(Collections
//          .singletonMap("server.port", "8081"));
//        app.run(args);
	}

}
