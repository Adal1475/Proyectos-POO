package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.EmpleadoDto;
import pe.edu.uni.app.service.EmpleadoService;

@RestController
@RequestMapping("/empleados")
@CrossOrigin("*")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoServicio;
	
	@PostMapping("/crear")
	public String crearEmpleado(@RequestBody EmpleadoDto dto) {
		String mensaje;
		try {
			dto = empleadoServicio.crearEmpleado(dto);
			mensaje = "Registro de empleado exitoso.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
