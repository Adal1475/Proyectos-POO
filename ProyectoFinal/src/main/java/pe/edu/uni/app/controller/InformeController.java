package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.InformeService;

@RestController
@RequestMapping("/presentar")
@CrossOrigin("*")
public class InformeController {
	
	@Autowired
	private InformeService informeservice;
	
	@GetMapping("/informe")
	public String calcularDescuento(){
		String mensaje = "";
		try {
			mensaje = informeservice.presentarInforme();
		} catch (Exception e) {
			mensaje = "" + e.getMessage();
		}
		return mensaje;
	}
}
