package pe.edu.uni.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ResumenCursosDto;
import pe.edu.uni.app.service.ResumenCursosService;



@RestController
@RequestMapping("/resumen")
public class ResumenCursosController {
	
	@Autowired
	private ResumenCursosService resumenCursosService;
	
	@GetMapping("/cursos")
	public List<Map<String, Object>> presentarResumen(@RequestBody ResumenCursosDto dto){
		return resumenCursosService.presentarResumen(dto);
	}

}

