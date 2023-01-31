package com.usuario.servicio.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.servicio.entidades.Usuario;
import com.usuario.servicio.feignclients.CocheFeignClient;
import com.usuario.servicio.feignclients.MotoFeignClient;
import com.usuario.servicio.modelos.Coche;
import com.usuario.servicio.modelos.Moto;
import com.usuario.servicio.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CocheFeignClient cocheFeignClient;
	@Autowired
	private MotoFeignClient motoFeignClient;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

//Ini-RestTempleate
	public List<Coche> getCoches(int usuarioId) {
		List<Coche> coches = restTemplate.getForObject("http://coche-servicio/coche/usuario/" + usuarioId, List.class);
		return coches;
	}

	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://moto-servicio/moto/usuario/" + usuarioId, List.class);
		return motos;
	}

// Fin-RestTempleate
	// Ini-FeignClient
	public Coche saveCoche(int usuarioId, Coche coche) {
		coche.setUsuarioId(usuarioId);
		Coche nuevo = cocheFeignClient.save(coche);
		return nuevo;
	}

	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevo = motoFeignClient.save(moto);
		return nuevo;
	}

	public Map<String, Object> getUsuarioyVehiculos(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
		if (usuario == null) {
			resultado.put("Mensaje", "El usuario " + usuarioId + " no existe");
			return resultado;
		}
		resultado.put("Usuario", usuario);
		List<Coche> coches = cocheFeignClient.getCoches(usuarioId);
		if (coches.isEmpty()) {
			resultado.put("Coches", "El usuario no tiene coches");
		} else {
			resultado.put("Coches", coches);
		}

		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if (motos.isEmpty()) {
			resultado.put("Motos", "El usuario no tiene motos");
		} else {
			resultado.put("Motos", motos);
		}
		return resultado;
	}
	// Fin-FeignClient

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
