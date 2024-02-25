package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ValidarIDPubDto;
import pe.edu.uni.app.service.ValidarIDPubService;

@RestController
@RequestMapping("/validar")
@CrossOrigin("*")
public class ValidarIDPubController {
	
	@Autowired
	private ValidarIDPubService adtarjetaservice;
	
	@GetMapping("/idpub")
	public String crearTarjeta(@RequestBody ValidarIDPubDto dto){
		String mensaje = "";
		try {
			dto = adtarjetaservice.valIDPub(dto);
			mensaje = "El ID de publicación: " + dto.getIdpublicacion() + " existe.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
