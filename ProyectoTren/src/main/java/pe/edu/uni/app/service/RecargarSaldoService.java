package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.TarjetaRecargaDto;

@Service
public class RecargarSaldoService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public TarjetaRecargaDto recargarSaldo(TarjetaRecargaDto dto) {
		
		//verificar tarjeta
		String sql= "Select count(1) filas from Tarjeta ";
		sql +="Where chr_tarjcodigo=?";
		int filas= jdbcTemplate.queryForObject(sql, Integer.class, dto.getTarjeta());
		if (filas==0) {
			throw new RuntimeException("Codigo de la tarjeta incorrecto.");
		}
		//verificar cliente
		sql= "Select count(1) filas from Cliente ";
		sql+="where chr_cliecodigo=?";
		filas= jdbcTemplate.queryForObject(sql, Integer.class, dto.getCliente());
		if (filas==0) {
			throw new RuntimeException("Codigo del cliente incorrecto.");
		}
		//verificar empleado
		sql= "Select count(1) filas from Empleado ";
		sql+="where chr_emplcodigo=?";
		filas= jdbcTemplate.queryForObject(sql, Integer.class, dto.getEmpleado());
		if (filas==0) {
			throw new RuntimeException("Codigo del empleado incorrecto.");
		}
		//haciendo la recarga
		if (dto.getDineroRecarga()>100) {
			throw new RuntimeException("No se puede recargar mas de 100 soles");
		}
		else {
			sql="update Tarjeta set dec_tarjsaldo= dec_tarjsaldo + "+ dto.getDineroRecarga();
			sql+= " where chr_tarjcodigo=?";
			jdbcTemplate.update(sql, dto.getTarjeta());
			//registrar movimiento
			sql="select count(*) filas from Movimiento where chr_tipocodigo= '004'";
			filas= jdbcTemplate.queryForObject(sql, Integer.class);
			filas++;
			sql = "insert into Movimiento";
			sql += " values( ?,? ,getdate(),?,?,'004')";
			jdbcTemplate.update(sql, dto.getTarjeta(), filas ,dto.getCliente(), dto.getEmpleado());
		}
		
		return dto;
	}
	
	
}
