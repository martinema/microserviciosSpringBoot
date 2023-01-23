package com.usuario.servicio.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.servicio.modelos.Coche;

@FeignClient(name="coche-servicio",url = "http://localhost:8082", path = "/coche")
//@RequestMapping("/coche")
public interface CocheFeignClient {

	@PostMapping()
	public Coche save(@RequestBody Coche coche);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Coche> getCoches(@PathVariable("usuarioId") int usuarioId);
}
