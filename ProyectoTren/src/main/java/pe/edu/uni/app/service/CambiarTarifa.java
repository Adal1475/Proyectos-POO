package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.CambiarTarifaDto;

@Service
public class CambiarTarifa {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CambiarTarifaDto cambiarTarifa(CambiarTarifaDto dto) {
		
		//verificar empleado
		String sql= "Select count(1) filas from Empleado ";
		sql+="where chr_emplcodigo= ?";
		int filas= jdbcTemplate.queryForObject(sql, Integer.class, dto.getEmpleado());
		if (filas==0) {
			throw new RuntimeException("Codigo del empleado incorrecto.");
		}
		//cambiando la tarifa
		sql= "update TipoTarjeta set float_tipotarjtarifa= "+ dto.getNuevaTarifa();
		sql+= " where chr_tipotarjcodigo= ?";
		jdbcTemplate.update(sql, dto.getTipo());
		//registrando movimiento
		sql="select count(*) filas from Movimiento where chr_tipocodigo= '008'";
		filas= jdbcTemplate.queryForObject(sql, Integer.class);
		filas++;
		sql = "insert into Movimiento";
		sql += " values( ?,? ,getdate(),?,?,'008')";
		jdbcTemplate.update(sql, null, filas ,null, dto.getEmpleado());
		
		return dto;
	}
}
