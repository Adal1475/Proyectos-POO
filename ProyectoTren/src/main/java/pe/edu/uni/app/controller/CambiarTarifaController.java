package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.CambiarTarifaDto;
import pe.edu.uni.app.service.CambiarTarifa;

@RestController
@RequestMapping("/tarifa")
public class CambiarTarifaController {
	
	@Autowired
	private CambiarTarifa cambiarTarifaService;
	
	@PutMapping ("/cambiar")
	public String cambiarTarifa(@RequestBody CambiarTarifaDto dto) {
		String mensaje = "";
		try {
			dto = cambiarTarifaService.cambiarTarifa(dto);
			mensaje = "cambio de tarifa exitoso.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
