package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.EliminarTarjetaDto;
import pe.edu.uni.app.service.EliminarTarjetaService;

@RestController
@RequestMapping ("/tarjetas")
public class EliminarTarjetaController {

	@Autowired
	private EliminarTarjetaService eliminartarjetaService;
	
	@DeleteMapping ("/eliminar")
	public String eliminarTarjeta(@RequestBody EliminarTarjetaDto dto) {
		String mensaje = "";
		try {
			dto = eliminartarjetaService.eliminarTarjeta(dto);
			mensaje = "tarjeta eliminada correctamente.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
