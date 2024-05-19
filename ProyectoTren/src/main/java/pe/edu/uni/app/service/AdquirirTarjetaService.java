package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.AdquirirTarjetaDto;

@Service
public class AdquirirTarjetaService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public AdquirirTarjetaDto crearTarjeta(AdquirirTarjetaDto dto) {
		
		//Validar Codigo Empleado
		String sql = "select count(1) cont from Empleado ";
		sql += "Where chr_emplcodigo = ?";
		int i = jdbctemplate.queryForObject(sql,Integer.class,dto.getEmpleado());
		if(i == 0) {
			throw new RuntimeException("Codigo de empleado incorrecto.");
		}
		//Validar Codigo Cliente
		sql = "select count(1) cont from Cliente ";
		sql += "Where chr_cliecodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class,dto.getCliente());
		if(i == 0) {
			throw new RuntimeException("Codigo de cliente incorrecto.");
		}
		
		//Validar Tipo Tarjeta
		sql = "select count(1) cont from TipoTarjeta ";
		sql += "Where chr_tipotarjcodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class,dto.getTipo());
		if(i == 0) {
			throw new RuntimeException("Tipo de tarjeta incorrecto (NORMAL 01 o MEDIO 02).");
		}
		if(dto.getTipo().equals("02")){
			sql = "select count(1) cont from Tarjeta ";
			sql += "Where chr_cliecodigo = ? and chr_tarjtipo = ?";
			i = jdbctemplate.queryForObject(sql, Integer.class,dto.getCliente(),dto.getTipo());
			if(i == 1) {
				throw new RuntimeException("Ya se creo una tarjeta de MEDIO con el cliente ingresado.");
			}
		}
		
		//Recuperar Codigo de Estacion
		sql = "Select chr_estacodigo from Asignado ";
		sql += "Where chr_emplcodigo = ?";
		String estaCodigo = jdbctemplate.queryForObject(sql, String.class, dto.getEmpleado());
		
		//Recuperar Contador de Tarjetas segun Estacion
		sql = "update Estacion set int_estaconttarj = int_estaconttarj + 1";
		sql += "Where chr_estacodigo = ?";
		jdbctemplate.update(sql,estaCodigo);
		sql = "update Estacion set int_estaultimoid = int_estaultimoid + 1";
		sql += "Where chr_estacodigo = ?";
		jdbctemplate.update(sql,estaCodigo);
		sql = "select int_estaultimoid from Estacion "; 
		sql += "Where chr_estacodigo = ?";
		int cont = jdbctemplate.queryForObject(sql,Integer.class,estaCodigo);
		
		//Crear Codigo de Tajerta
		String formate = "%04d";
		String tarjCodigo = estaCodigo + String.format(formate, cont);
		
		//Registrar Tarjeta
		sql = "Insert into Tarjeta ";
		sql += "Values(?,?,?,?,?,0,getdate())";
		jdbctemplate.update(sql,tarjCodigo,dto.getTipo(),estaCodigo,dto.getEmpleado(),dto.getCliente());
		
		//Registrar movimiento
		String movi;
		if(dto.getTipo().equals("01")) {
			movi = "001";
		}
		else {
			movi = "002";
		}
		//contando cuantos registros ya hay
		sql = "select count(*) cont from Movimiento ";
		sql += " Where chr_tipocodigo = ?";
		cont = jdbctemplate.queryForObject(sql,Integer.class, movi);
		cont += 1;
		//insertado el movimiento
		sql = "Insert into Movimiento ";
		sql += "Values (?,?,getdate(),?,?,?)";
		jdbctemplate.update(sql,tarjCodigo,cont,dto.getCliente(),dto.getEmpleado(),movi);
		
		dto.setCodigo(tarjCodigo);
		
		return dto;
	}
}
