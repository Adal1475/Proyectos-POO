package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.CalcularDescuentoDto;
import pe.edu.uni.app.service.CalcularDescuentoService;

@RestController
@RequestMapping("/calcular")
@CrossOrigin("*")
public class CalcularDescuentoController {
	
	@Autowired
	private CalcularDescuentoService calcdescservice;
	
	@GetMapping("/descuento")
	public String calcularDescuento(@RequestBody CalcularDescuentoDto dto){
		String mensaje = "";
		try {
			dto = calcdescservice.calcularDescuento(dto);
			mensaje = "El descuento es: " + dto.getDescuento();
		} catch (Exception e) {
			mensaje = "" + e.getMessage();
		}
		return mensaje;
	}
}
