package com.usuario.servicio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.servicio.entidades.Usuario;
import com.usuario.servicio.modelos.Coche;
import com.usuario.servicio.modelos.Moto;
import com.usuario.servicio.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public List<Coche> getCoches(int usuarioId) {
		List<Coche> coches = restTemplate.getForObject("http://localhost:8082/Coche/usuario/" + usuarioId, List.class);
		return coches;
	}


	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8083/Moto/usuario/" + usuarioId, List.class);
		return motos;
	}
	
	public List<Usuario> getAll() {
		return usuarioRepositorio.findAll();
	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevo = usuarioRepositorio.save(usuario);
		return nuevo;
	}
}
