package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ClienteDto;
import pe.edu.uni.app.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/crear")
	public String crearCliente(@RequestBody ClienteDto dto) {
		String mensaje;
		try {
			dto = clienteService.crearCliente(dto);
			mensaje = "Registro de cliente exitoso.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}
