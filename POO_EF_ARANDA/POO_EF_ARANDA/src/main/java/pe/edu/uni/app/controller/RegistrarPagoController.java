package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.RegistrarPagoDto;
import pe.edu.uni.app.service.RegistrarPagoService;

@RestController
@RequestMapping("/pago")
@CrossOrigin("*")
public class RegistrarPagoController {
	
	@Autowired
	private RegistrarPagoService pagoservice;
	
	@GetMapping("/validarmatricula")
	public String validarMatricula(@RequestBody RegistrarPagoDto dto){
		String mensaje = "";
		try {
			dto = pagoservice.registrarPago(dto);
			mensaje = "La matrícula existe con los datos ingresados.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/validarcuota")
	public String validarCuota(@RequestBody RegistrarPagoDto dto){
		String mensaje = "";
		try {
			dto = pagoservice.registrarPago(dto);
			mensaje = "La cuota a pagar es válida.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/validarimporte")
	public String validarImporte(@RequestBody RegistrarPagoDto dto){
		String mensaje = "";
		try {
			dto = pagoservice.registrarPago(dto);
			mensaje = "El importe a pagar es válido.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@PostMapping("/registrarpago")
	public String registrarPago(@RequestBody RegistrarPagoDto dto){
		String mensaje = "";
		try {
			dto = pagoservice.registrarPago(dto);
			mensaje = "El pago se realizó con exito.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}

