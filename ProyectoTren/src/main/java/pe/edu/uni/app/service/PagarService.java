package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.PagarDto;

@Service
public class PagarService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public PagarDto pagar(PagarDto dto) {
		
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
			throw new RuntimeException("Codigo del cliente incorrecto.");
		}
		//Recuperamos el tipo de tarjeta
		sql="Select chr_tarjtipo from Tarjeta";
		sql+=" where chr_tarjcodigo=?";
		String tipo= jdbcTemplate.queryForObject(sql, String.class, dto.getTarjeta());
		//Recuperamos el dinero a pagar
		sql="Select float_tipotarjtarifa from TipoTarjeta";
		sql+=" where chr_tipotarjcodigo=?";
		float tarifa= jdbcTemplate.queryForObject(sql, float.class, tipo);
		//Recuperando el saldo
		sql="Select dec_tarjsaldo from Tarjeta";
		sql+=" where chr_tarjcodigo=?";
		float saldo= jdbcTemplate.queryForObject(sql, float.class, dto.getTarjeta());
		
		//pagando
		if (saldo<tarifa) {
			throw new RuntimeException("Saldo en la tarjeta Insuficiente");
		}
		else {
			sql="Update Tarjeta set dec_tarjsaldo= dec_tarjsaldo - "+ tarifa;
			sql+=" where chr_tarjcodigo=?";
			jdbcTemplate.update(sql, dto.getTarjeta());
			//Agregar el dinero a la cuenta de la estacion
			sql="update Estacion set int_estacuenta= int_estacuenta + "+ tarifa;
			sql+= " where chr_estacodigo=?";
			jdbcTemplate.update(sql, dto.getEstacion());
			//Registra mov
			sql="select count(*) filas from Movimiento where chr_tipocodigo= '005'";
			filas= jdbcTemplate.queryForObject(sql, Integer.class);
			filas++;
			sql = "insert into Movimiento";
			sql += " values( ?,? ,getdate(),?,?,'005')";
			jdbcTemplate.update(sql, dto.getTarjeta(), filas ,dto.getCliente(), dto.getEmpleado());
		}
		
		return dto;
	}
}
