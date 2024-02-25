package pe.edu.uni.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.ResumenCuentasService;

@RestController
@RequestMapping("/resumen")
public class ResumenCuentasController {
	
	@Autowired
	private ResumenCuentasService resumenCuentasService;
	
	@GetMapping("/cuentas")
	public List<Map<String, Object>> presentarResumen(){
		return resumenCuentasService.presentarResumen();
	}

}
