package com.usuario.servicio.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.servicio.modelos.Moto;

@FeignClient(name="moto-servicio")
//@RequestMapping("/moto")
public interface MotoFeignClient {

	@PostMapping()
	public Moto save(@RequestBody Moto moto);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Moto> getMotos(@PathVariable("usuarioId") int usuarioId);
}
