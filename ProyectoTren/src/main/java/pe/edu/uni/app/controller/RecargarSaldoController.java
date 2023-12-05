package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.TarjetaRecargaDto;
import pe.edu.uni.app.service.RecargarSaldoService;

@RestController
@RequestMapping("/tarjetas")
@CrossOrigin("*")
public class RecargarSaldoController {
	
	@Autowired
	private RecargarSaldoService recargarService;
	
	@PutMapping ("/recargar")
	public String recargarSaldo(@RequestBody TarjetaRecargaDto dto) {
		String mensaje="";
		try {
			dto= recargarService.recargarSaldo(dto);
			mensaje= "Recarga de tarjeta exitosa";
		} catch (Exception e) {
			mensaje= "Error: " + e.getMessage();
		}
		
		return mensaje;
	}
}
