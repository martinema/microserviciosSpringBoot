package com.coche.servicio.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coche.servicio.entidades.Coche;
import com.coche.servicio.servicios.CocheServicio;

@RestController
@RequestMapping("/coche")
public class CocheControlador {

		 @Autowired
		 private CocheServicio cocheService;
		 
		 @GetMapping
		 public ResponseEntity<List<Coche>> listarCoches(){
			 List <Coche> coche = cocheService.getAll();
			 if(coche.isEmpty()) {
				 return ResponseEntity.noContent().build();
			 }
			 return ResponseEntity.ok(coche);
		 }
		 
		 @GetMapping("/{id}")
		 public ResponseEntity<Coche> obtenerCoche(@PathVariable("id") int id){
			 Coche usuario = cocheService.getCocheById(id);
			 if(usuario == null) {
				 return ResponseEntity.notFound().build();
			 }
			 return ResponseEntity.ok(usuario);
		 }
		 
		 @PostMapping
		 public ResponseEntity<Coche> guardarCoche(@RequestBody Coche coche){
			 Coche nuevo = cocheService.save(coche);
			 return ResponseEntity.ok(nuevo);
		 }
		 

		 
		 @GetMapping("/usuario/{usuarioId}")
		 public ResponseEntity<List<Coche>> listarCochesPorUsuarioId(@PathVariable("usuarioId") int usuarioId){
			 List<Coche> coches = cocheService.byUsuarioId(usuarioId);
			 if(coches == null) {
				 return ResponseEntity.noContent().build();
			 }
			 return ResponseEntity.ok(coches);
		 }
		 
}
