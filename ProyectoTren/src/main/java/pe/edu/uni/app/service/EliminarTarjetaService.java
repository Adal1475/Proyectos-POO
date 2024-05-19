package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.EliminarTarjetaDto;

@Service
public class EliminarTarjetaService {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public EliminarTarjetaDto eliminarTarjeta(EliminarTarjetaDto dto) {
	
		//verificar empleado
		String sql= "Select count(1) filas from Empleado ";
		sql+="where chr_emplcodigo= ?";
		int filas= jdbctemplate.queryForObject(sql, Integer.class, dto.getEmpleado());
		if (filas==0) {
			throw new RuntimeException("Codigo del empleado incorrecto.");
		}
		//validar codigo tarjeta
		sql = "select count(*) cont from Tarjeta ";
		sql += "Where chr_tarjcodigo = ?";
		filas = jdbctemplate.queryForObject(sql, Integer.class,dto.getTarjeta());
		if(filas==0) {
			throw new RuntimeException("Codigo de tarjeta incorrecto.");
		}
		//recuperamos codigo de estacion y cliente
		sql= "select chr_estacodigo from Tarjeta ";
		sql += " where chr_tarjcodigo = ?";
		String estacod= jdbctemplate.queryForObject(sql, String.class, dto.getTarjeta());
		
		sql= "select chr_cliecodigo from Tarjeta ";
		sql += " where chr_tarjcodigo = ?";
		String cliecod= jdbctemplate.queryForObject(sql, String.class, dto.getTarjeta());
		
		//actualizar contador de tarjetas
		sql= "update Estacion set int_estaconttarj= int_estaconttarj - 1 "; 
		sql+= " where chr_estacodigo=?";
		jdbctemplate.update(sql, estacod);
		
		//Registrar movimiento
		sql="select count(*) filas from Movimiento where chr_tipocodigo= '003'";
		filas= jdbctemplate.queryForObject(sql, Integer.class);
		filas++;
		sql="insert into Movimiento ";
		sql+=" values(?,?, getdate(),?,?, '003')";
		jdbctemplate.update(sql, dto.getTarjeta(), filas, cliecod, dto.getEmpleado() );
		
		//eliminar tarjeta
		sql= "delete from Tarjeta ";
		sql +=" where chr_tarjcodigo=?";
		jdbctemplate.update(sql, dto.getTarjeta());
		return dto;
	}

}
