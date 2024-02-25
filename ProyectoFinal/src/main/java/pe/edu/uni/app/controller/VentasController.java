package pe.edu.uni.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.VentasDto;
import pe.edu.uni.app.service.VentasService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin("*")
public class VentasController {
	
	@Autowired
	private VentasService ventaservice;
	
	@GetMapping("/validaridpub")
	public String validarIDPub(@RequestBody VentasDto dto) {
		String mensaje = "";
		try {
			dto.setEmpleusuario("eaguero");
			dto.setEmplecontra("cazador");
			dto = ventaservice.nuevaVenta(dto);
			mensaje = "ID ingresado: " + dto.getIdpublicacion() + "\nEl ID ingresado es válido.";
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/calculardescuento")
	public String calcularDescuento(@RequestBody VentasDto dto) {
		String mensaje = "";
		try {
			dto.setEmpleusuario("eaguero");
			dto.setEmplecontra("cazador");
			dto.setIdpublicacion("LIB00001");
			dto = ventaservice.nuevaVenta(dto);
			mensaje = "El descuento es: " + dto.getDescuento();
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/calcularventa")
	public String calcularVenta(@RequestBody VentasDto dto) {
		String mensaje = "";
		try {
			dto.setEmpleusuario("eaguero");
			dto.setEmplecontra("cazador");
			dto = ventaservice.nuevaVenta(dto);
			mensaje = "Resumen de venta:\n----------------------------";
			mensaje += "\nProducto: " + dto.getDescpublicacion();
			mensaje += "\nPrecio: " + dto.getPrecioventa();
			mensaje += "\nCantidad: " + dto.getCantventa();
			mensaje += "\n";
			mensaje += "\nDescuento: " + dto.getDescuento();
			mensaje += "\nSubtotal: " + dto.getSubtotal();
			mensaje += "\nImpuesto: " + dto.getImpuesto();
			mensaje += "\nTotal:: " + dto.getTotal();
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@GetMapping("/validarstock")
	public String validarStock(@RequestBody VentasDto dto) {
		String mensaje = "";
		try {
			dto.setEmpleusuario("eaguero");
			dto.setEmplecontra("cazador");
			dto = ventaservice.nuevaVenta(dto);
			mensaje = "Resumen de venta:\n----------------------------";
			mensaje += "\nProducto: " + dto.getDescpublicacion();
			mensaje += "\nPrecio: " + dto.getPrecioventa();
			mensaje += "\nCantidad: " + dto.getCantventa();
			mensaje += "\n";
			mensaje += "\nDescuento: " + dto.getDescuento();
			mensaje += "\nSubtotal: " + dto.getSubtotal();
			mensaje += "\nImpuesto: " + dto.getImpuesto();
			mensaje += "\nTotal:: " + dto.getTotal();
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
	
	@PostMapping("/nuevaventa")
	public String nuevaVenta(@RequestBody VentasDto dto){
		String mensaje = "";
		try {
			dto = ventaservice.nuevaVenta(dto);
			mensaje = "Resumen de venta:\n----------------------------";
			mensaje += "\nNombre del vendedor: " + dto.getEmplenombre();
			mensaje += "\nNombre del cliente: " + dto.getCliente();
			mensaje += "\n";
			mensaje += "\nProducto: " + dto.getDescpublicacion();
			mensaje += "\nPrecio: " + dto.getPrecioventa();
			mensaje += "\nCantidad: " + dto.getCantventa();
			mensaje += "\n";
			mensaje += "\nDescuento: " + dto.getDescuento();
			mensaje += "\nSubtotal: " + dto.getSubtotal();
			mensaje += "\nImpuesto: " + dto.getImpuesto();
			mensaje += "\nTotal:: " + dto.getTotal();
		} catch (Exception e) {
			mensaje = "Error: " + e.getMessage();
		}
		return mensaje;
	}
}