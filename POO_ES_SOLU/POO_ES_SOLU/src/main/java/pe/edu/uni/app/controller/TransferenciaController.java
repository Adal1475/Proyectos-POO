package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.TransferenciaDto;
import pe.edu.uni.app.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin("*")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaservice;
	
	@GetMapping("/validarcuentas")
	public String validarCuentas(@RequestBody TransferenciaDto dto){
		String mensaje = "";
		try {
			dto = transferenciaservice.realizarTransferencia(dto,1);
			mensaje = "Las cuentas origen y destino existen y están activas.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/validarmoneda")
	public String validarMoneda(@RequestBody TransferenciaDto dto){
		String mensaje = "";
		try {
			dto = transferenciaservice.realizarTransferencia(dto,2);
			mensaje = "Las cuentas origen y destino poseen la misma moneda.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/validarimporte")
	public String validarImporte(@RequestBody TransferenciaDto dto){
		String mensaje = "";
		try {
			dto = transferenciaservice.realizarTransferencia(dto,3);
			mensaje = "El importe es positivo.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/validarsaldo")
	public String validarSaldo(@RequestBody TransferenciaDto dto){
		String mensaje = "";
		try {
			dto = transferenciaservice.realizarTransferencia(dto,4);
			mensaje = "El saldo en la cuenta origen es suficiente para efectuar la transacción.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@PostMapping("/realizartransferencia")
	public String realizarTransferencia(@RequestBody TransferenciaDto dto){
		String mensaje = "";
		try {
			dto = transferenciaservice.realizarTransferencia(dto,5);
			mensaje = "La transferencia se realizó con éxito.";
			
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}