package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.CalcularVentaDto;
import pe.edu.uni.app.service.CalcularVentaService;

@RestController
@RequestMapping("/calcular")
@CrossOrigin("*")
public class CalcularVentaController {
	
	@Autowired
	private CalcularVentaService calcventaservice;
	
	@GetMapping("/venta")
	public String calcularDescuento(@RequestBody CalcularVentaDto dto){
		String mensaje = "";
		try {
			dto = calcventaservice.calcularVenta(dto);
			mensaje = "Resumen de Venta:";
			mensaje += "\nID de publicación: " + dto.getIdpublicacion();
			mensaje += "\nPrecio de venta: " + dto.getPrecioventa();
			mensaje += "\nCantidad vendida: " + dto.getCantventa();
			mensaje += "\nDescuento: " + dto.getDescuento();
			mensaje += "\n----------------------------------";
			mensaje += "\nSubtotal: " + dto.getSubtotal();
			mensaje += "\nImpuesto: " + dto.getImpuesto();
			mensaje += "\nTotal: " + dto.getTotal();
		} catch (Exception e) {
			mensaje = "" + e.getMessage();
		}
		return mensaje;
	}
}