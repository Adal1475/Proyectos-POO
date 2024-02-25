package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ValidarStockDto;
import pe.edu.uni.app.service.ValidarStockService;

@RestController
@RequestMapping("/validar")
@CrossOrigin("*")
public class ValidarStockController {
	
	@Autowired
	private ValidarStockService valstockservice;
	
	@GetMapping("/stock")
	public String valStock(@RequestBody ValidarStockDto dto){
		String mensaje = "";
		try {
			dto = valstockservice.valStock(dto);
			mensaje = "Aún hay stock del producto.\nStock disponible: " + dto.getContador() + "\nStock luego de la venta: " + (dto.getContador() + dto.getIncremento());
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}