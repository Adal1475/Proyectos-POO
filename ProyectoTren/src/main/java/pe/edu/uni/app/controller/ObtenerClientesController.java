package pe.edu.uni.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.ObtenerClientesService;


@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ObtenerClientesController {
	
	@Autowired
	private ObtenerClientesService obtenerclientesservice;
	
	@GetMapping("/mostrar")
	public List<Map<String, Object>> getClientes(){
		return obtenerclientesservice.getClientes();
	}
}
