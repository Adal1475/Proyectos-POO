package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ReporteDto;
import pe.edu.uni.app.service.ReporteService;

@RestController
@RequestMapping("/presentar")
@CrossOrigin("*")
public class ReporteController {
	
	@Autowired
	private ReporteService reporteservice;
	
	@GetMapping("/reporte")
	public String presentarReporte(@RequestBody ReporteDto dto){
		String mensaje = "";
		try {
			dto = reporteservice.presentarReporte(dto);
			mensaje = "Alumno: " + dto.getAlumno();
			mensaje += "\nCurso: " + dto.getCurso();
			mensaje += "\nTipo: " + dto.getTipo();
			mensaje += "\nPrecio: " + dto.getPrecio();
			mensaje += "\nCuotas: " + dto.getCuotas();
			mensaje += "\nNota: " + dto.getNota();
			
			mensaje += "\nNro. Cuota\tFecha\tImporte";
			mensaje += "\n-------------------------------------";
			for(int i = 0; i < dto.getCuotas(); i++) {
				mensaje += "\n" + dto.getPagos()[i];
			}
			mensaje += "\n-------------------------------------\n";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
