package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.AdquirirTarjetaDto;
import pe.edu.uni.app.service.AdquirirTarjetaService;

@RestController
@RequestMapping("/tarjetas")
public class AdquirirTarjetaController {
	
	@Autowired
	private AdquirirTarjetaService adtarjetaservice;
	
	@PostMapping("/crear")
	public String crearTarjeta(@RequestBody AdquirirTarjetaDto dto){
		String mensaje = "";
		try {
			dto = adtarjetaservice.crearTarjeta(dto);
			mensaje = "Registro de tarjeta exitoso.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
