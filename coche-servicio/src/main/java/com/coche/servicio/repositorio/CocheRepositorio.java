package com.coche.servicio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coche.servicio.entidades.Coche;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche, Integer>{
	List<Coche> findByUsuarioId(int usuarioId);
}
