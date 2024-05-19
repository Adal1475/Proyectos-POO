package pe.edu.uni.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.ObtenerTarjetasService;

@RestController
@RequestMapping("/tarjetas")
public class ObtenerTarjetasController {
	
	@Autowired
	private ObtenerTarjetasService obtetarjService;
	
	@GetMapping("/mostrar")
	public List<Map<String, Object>> getTarjetas(){
		return obtetarjService.getTarjates();
	}

}
