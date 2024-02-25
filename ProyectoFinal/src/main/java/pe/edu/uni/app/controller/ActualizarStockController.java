package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ActualizarStockDto;
import pe.edu.uni.app.service.ActualizarStockService;

@RestController
@RequestMapping("/actualizar")
@CrossOrigin("*")
public class ActualizarStockController {
	
	@Autowired
	private ActualizarStockService actstockservice;
	
	@PostMapping("/stock")
	public String actualizarStock(@RequestBody ActualizarStockDto dto){
		String mensaje = "";
		try {
			dto = actstockservice.actualizarStock(dto);
			mensaje = "El stock del item: '" + dto.getIdtipo() + "' se actualizó correctamente. Stock actual: " + dto.getContador();
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}