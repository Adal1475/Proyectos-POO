package pe.edu.uni.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.ResumenMovimientoService;



@RestController
@RequestMapping("/resumen")
public class ResumenMovimientoController {
	
	@Autowired
	private ResumenMovimientoService resumenMovsService;
	
	@GetMapping("/movimientos")
	public List<Map<String, Object>> presentarResumen(){
		return resumenMovsService.presentarResumen();
	}

}
