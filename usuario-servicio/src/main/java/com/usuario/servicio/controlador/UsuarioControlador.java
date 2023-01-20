package com.usuario.servicio.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	 @Autowired
	 private UsuarioService usuarioService;
	 
	 @GetMapping
	 public ResponseEntity<List<Usuario>> listarUsuarios(){
		 List <Usuario> usuarios = usuarioService.getAll();
		 if(usuarios.isEmpty()) {
			 return ResponseEntity.noContent().build();
		 }
		 return ResponseEntity.ok(usuarios);
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		 Usuario usuario = usuarioService.getUsuarioById(id);
		 if(usuario == null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok(usuario);
	 }
	 
	 @PostMapping
	 public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		 Usuario nuevo = usuarioService.save(usuario);
		 return ResponseEntity.ok(nuevo);
	 }
	 
	 @GetMapping("/coches/{usuarioId}")
	 public ResponseEntity <List<Coche>> getCoches(@PathVariable ("usuarioId") int usuarioId){
		 Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		 if(usuario==null) {
			 return ResponseEntity.notFound().build();
		 }
		 List <Coche> coches = usuarioService.getCoches(usuarioId);
		 return ResponseEntity.ok(coches);
	 }
	 
	 @GetMapping("/motos/{usuarioId}")
	 public ResponseEntity <List<Moto>> getMotos(@PathVariable ("usuarioId") int usuarioId){
		 Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		 if(usuario==null) {
			 return ResponseEntity.notFound().build();
		 }
		 List <Moto> motos = usuarioService.getMotos(usuarioId);
		 return ResponseEntity.ok(motos);
	 }
}
