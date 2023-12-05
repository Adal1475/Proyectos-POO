package pe.edu.uni.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.ObtenerEmpleadosService;

@RestController
@RequestMapping("/empleados")
@CrossOrigin("*")
public class ObtenerEmpleadoController {

	@Autowired
	private ObtenerEmpleadosService obtemplservice;
	
	@GetMapping("/mostrar")
	public List<Map<String, Object>> getEmpleados(){
		return obtemplservice.getEmpleados();
	}
}
