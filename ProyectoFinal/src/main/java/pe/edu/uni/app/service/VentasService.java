package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.VentasDto;

@Service
public class VentasService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public VentasDto nuevaVenta(VentasDto dto) {
		String sql;
		System.out.print("A:"+dto.getEmplecontra());
		//Validar empleado
		int idempleado = 0;
		sql = "select idempleado from USUARIO where usuario = ? and clave = ?";
		idempleado = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEmpleusuario(), dto.getEmplecontra());
		if(idempleado == 0) {
			throw new RuntimeException("El nombre de usuario ingresado o la contraseña son inválidos.");
		}
		dto.setIdempleado(idempleado);
		
		sql = "select activo from USUARIO ";
		sql += "where usuario = ?";
		int activo = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEmpleusuario());
		if(activo == 0) {
			throw new RuntimeException("El empleado no está activo.");
		}
		
		//Obtener empleado
		sql = "select apellido from EMPLEADO where idempleado = ?";
		String emplenombre = jdbcTemplate.queryForObject(sql, String.class, dto.getIdempleado());
		sql = "select nombre from EMPLEADO where idempleado = ?";
		emplenombre += " " + jdbcTemplate.queryForObject(sql, String.class,dto.getIdempleado());
		dto.setEmplenombre(emplenombre);
		
		//Validar ID publicacion
		sql = "select count(1) idpublicacion from PUBLICACION ";
				sql += "where idpublicacion = ?";
		int i = jdbcTemplate.queryForObject(sql, Integer.class, dto.getIdpublicacion());
		if(i == 0) {
			throw new RuntimeException("ID de publicación ingresado: " + dto.getIdpublicacion() + "\n El ID de publicación no es válido.");
		}
		
		//Obtener el título de la publicación
		sql = "select titulo from PUBLICACION where idpublicacion = ?";
		String descpublicacion = jdbcTemplate.queryForObject(sql, String.class, dto.getIdpublicacion());
		dto.setDescpublicacion(descpublicacion);
		
		//Calcular descuento
		sql = "select porcentaje from PROMOCION ";
		sql += "where cantmin <= ? and cantmax >= ?";
		float descuento = jdbcTemplate.queryForObject(sql, Float.class, dto.getCantventa(),dto.getCantventa());
		dto.setDescuento(descuento);
		
		//Calcular venta
		sql = "select precio from PUBLICACION ";
		sql += "where idpublicacion = ?";
		float precioventa = jdbcTemplate.queryForObject(sql, Float.class, dto.getIdpublicacion());
		dto.setPrecioventa(precioventa);
		
		sql = "select valor from control where parametro = 'IGV'";
		float igv = jdbcTemplate.queryForObject(sql, Float.class);
		
		float cantventa = dto.getCantventa();
		float total = (precioventa*cantventa)*(1-descuento);
		float impuesto = total*igv;
		float subtotal = total - impuesto;
		
		dto.setImpuesto(impuesto);
		dto.setSubtotal(subtotal);
		dto.setTotal(total);
		
		//Validar Stock
		sql = "select stock from PUBLICACION ";
		sql += "where idpublicacion = ?";
		int stock = jdbcTemplate.queryForObject(sql, Integer.class, dto.getIdpublicacion());
		
		if(stock < cantventa) {
			throw new RuntimeException("El stock es insuficiente. Stock restante: " + stock);
		}
		
		//Actualizar Stock
		sql = "update PUBLICACION set stock = stock + ? ";
		sql += "where idpublicacion = ?";
		jdbcTemplate.update(sql);
		
		//Generar IDVENTA
		sql = "select valor from control where parametro = 'VENTA'";
		int idventa = jdbcTemplate.queryForObject(sql, Integer.class);
		idventa = idventa +1;
		dto.setIdventa(idventa);
		
		//Registrar venta
		sql = "update CONTROL set valor = ? ";
		sql	+= "where parametro = 'VENTA'";
		jdbcTemplate.update(sql,dto.getIdventa());
				
		sql = "insert into VENTA values (?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,dto.getIdventa(), dto.getCliente(), dto.getIdempleado(), dto.getIdpublicacion(), dto.getCantventa(), dto.getPrecioventa(), dto.getDescuento(), dto.getSubtotal(), dto.getImpuesto(), dto.getTotal());
		
		return dto;
	}
}
