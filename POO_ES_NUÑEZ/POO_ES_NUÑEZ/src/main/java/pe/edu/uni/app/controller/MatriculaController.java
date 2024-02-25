package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.MatriculaDto;
import pe.edu.uni.app.service.MatriculaService;


@RestController
@RequestMapping("/matricula")
@CrossOrigin("*")
public class MatriculaController {
	
	@Autowired
	private MatriculaService matriculaservice;
	
	@GetMapping("/comprobarexistente")
	public String comprobarExistente(@RequestBody MatriculaDto dto){
		String mensaje = "";
		try {
			dto = matriculaservice.matricular(dto,1);
			mensaje = "El estudiante aún no se ha matriculado.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/cursoactivo")
	public String cursoActivo(@RequestBody MatriculaDto dto){
		String mensaje = "";
		try {
			dto = matriculaservice.matricular(dto,2);
			mensaje = "El curso está activo.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/comprobarvacantes")
	public String comprobarVacantes(@RequestBody MatriculaDto dto){
		String mensaje = "";
		try {
			dto = matriculaservice.matricular(dto,3);
			mensaje = "Todavía hay vacantes disponibles.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/comprobarciclo")
	public String comprobarCiclo(@RequestBody MatriculaDto dto){
		String mensaje = "";
		try {
			dto = matriculaservice.matricular(dto,4);
			mensaje = "El curso programado y el ciclo son correctos.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@PostMapping("/cambiarvacantes")
	public String cambiarVacantes(@RequestBody MatriculaDto dto){
		String mensaje = "";
		try {
			dto = matriculaservice.matricular(dto,5);
			mensaje = "Las vacantes y los matriculados han sido actualizados.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@PostMapping("/matricular")
	public String matricular(@RequestBody MatriculaDto dto){
		String mensaje = "";
		try {
			dto = matriculaservice.matricular(dto,6);
			mensaje = "La matrícula se realizó correctamente.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
