package com.moto.servicio.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.servicio.entidades.Moto;
import com.moto.servicio.servicios.MotoServicio;

@RestController
@RequestMapping("/moto")
public class MotoControlador {

	@Autowired
	private MotoServicio motoService;

	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos() {
		List<Moto> moto = motoService.getAll();
		if (moto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(moto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {
		Moto usuario = motoService.getMotoById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
		Moto nuevo = motoService.save(moto);
		return ResponseEntity.ok(nuevo);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
		List<Moto> motos = motoService.byUsuarioId(usuarioId);
		if (motos == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

}