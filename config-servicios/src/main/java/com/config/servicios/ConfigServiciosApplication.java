package com.config.servicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServiciosApplication.class, args);
	}

}
