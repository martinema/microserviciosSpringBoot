package com.usuario.servicio.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.servicio.entidades.Usuario;
import com.usuario.servicio.modelos.Coche;
import com.usuario.servicio.modelos.Moto;
import com.usuario.servicio.servicio.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario nuevo = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevo);
	}

	@CircuitBreaker(name = "cochesCB", fallbackMethod = "fallBackGetCoches")
	@GetMapping("/coches/{usuarioId}")
	public ResponseEntity<List<Coche>> getCoches(@PathVariable("usuarioId") int usuarioId) {
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Coche> coches = usuarioService.getCoches(usuarioId);
		return ResponseEntity.ok(coches);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") int usuarioId) {
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = usuarioService.getMotos(usuarioId);
		return ResponseEntity.ok(motos);
	}

	@CircuitBreaker(name = "cochesCB", fallbackMethod = "fallBackSaveCoches")
	@PostMapping("/coche/{usuarioId}")
	public ResponseEntity<Coche> guardarCoche(@PathVariable("usuarioId") int usuarioId, @RequestBody Coche coche) {
		Coche nuevo = usuarioService.saveCoche(usuarioId, coche);
		return ResponseEntity.ok(nuevo);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackSaveMotos")
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
		Moto nuevo = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevo);
	}

	@CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetTodos")
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId) {
		Map<String, Object> resultado = usuarioService.getUsuarioyVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}

	private ResponseEntity<List<Coche>> fallBackGetCoches(@PathVariable("usuarioId") int id,
			RuntimeException excepcion) {
		return new ResponseEntity("El usuario: " + id + " tiene los coches en el taller", HttpStatus.OK);
	}

	private ResponseEntity<Coche> fallBackSaveCoches(@PathVariable("usuarioId") int id, @RequestBody Coche coche,
			RuntimeException excepcion) {
		return new ResponseEntity("El usuario: " + id + " no tiene dinero para coches", HttpStatus.OK);
	}

	private ResponseEntity<List<Moto>> fallBackGetMotos(@PathVariable("usuarioId") int id, RuntimeException excepcion) {
		return new ResponseEntity("El usuario: " + id + " tiene las motos en el taller", HttpStatus.OK);
	}

	private ResponseEntity<Moto> fallBackSaveMotos(@PathVariable("usuarioId") int id, @RequestBody Moto moto,
			RuntimeException excepcion) {
		return new ResponseEntity("El usuario: " + id + " no tiene dinero para motos", HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> fallBackGetTodos(@PathVariable("usuarioId") int id,
			RuntimeException excepcion) {
		return new ResponseEntity("El usuario: " + id + " tiene los vehículos en el taller", HttpStatus.OK);
	}
}
