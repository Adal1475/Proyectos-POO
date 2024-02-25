package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.GenerarIDVentaDto;
import pe.edu.uni.app.service.GenerarIDVentaService;

@RestController
@RequestMapping("/generar")
@CrossOrigin("*")
public class GenerarIDVentaController {
	
	@Autowired
	private GenerarIDVentaService genidventaservice;
	
	@GetMapping("/idventa")
	public String generarIDVenta(GenerarIDVentaDto dto){
		String mensaje = "";
		try {
			dto = genidventaservice.generarIDVenta(dto);
			mensaje = "El ID de venta es: " + dto.getIdventa();
		} catch (Exception e) {
			mensaje = "" + e.getMessage();
		}
		return mensaje;
	}
}
