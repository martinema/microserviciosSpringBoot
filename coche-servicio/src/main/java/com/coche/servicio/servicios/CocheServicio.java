package com.coche.servicio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coche.servicio.entidades.Coche;
import com.coche.servicio.repositorio.CocheRepositorio;

@Service
public class CocheServicio {

	@Autowired
	private CocheRepositorio cocheRepositorio;

	public List<Coche> getAll() {
		return cocheRepositorio.findAll();
	}

	public Coche getCocheById(int id) {
		return cocheRepositorio.findById(id).orElse(null);
	}

	public Coche save(Coche coche) {
		Coche nuevo = cocheRepositorio.save(coche);
		return nuevo;
	}

	public List<Coche> byUsuarioId(int usuarioId) {
		return cocheRepositorio.findByUsuarioId(usuarioId);
	}
}
