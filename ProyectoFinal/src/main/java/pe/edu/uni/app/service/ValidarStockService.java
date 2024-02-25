package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ValidarStockDto;

@Service
public class ValidarStockService {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public ValidarStockDto valStock(ValidarStockDto dto) {
		String sql = "select count(1) idtipo from Tipo ";
		sql += "Where idtipo = ?";
		int i = jdbctemplate.queryForObject(sql,Integer.class,dto.getIdtipo());
		if(i == 0) {
			throw new RuntimeException("El ID de tipo " + dto.getIdtipo() + " no existe.");
		}
		
		sql = "select contador from tipo ";
		sql += "where idtipo = ?";
		int contador = jdbctemplate.queryForObject(sql,Integer.class,dto.getIdtipo());
		dto.setContador(contador);
		
		if(contador - dto.getIncremento() < 0) {
			throw new RuntimeException("No hay stock suficiente para el producto.\nStock disponible: " + dto.getContador());
		}
		return dto;
	}
}
