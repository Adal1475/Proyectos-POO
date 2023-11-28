package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.PagarDto;
import pe.edu.uni.app.service.PagarService;

@RestController
@RequestMapping ("/Boleto")
public class PagarController {
	
	@Autowired
	private PagarService pagarService;
	
	@PutMapping ("/pagar")
	public String pagar(@RequestBody PagarDto dto) {
		String mensaje="";
		try {
			dto= pagarService.pagar(dto);
			mensaje= "Pago exitoso";
		} catch (Exception e) {
			mensaje= "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
