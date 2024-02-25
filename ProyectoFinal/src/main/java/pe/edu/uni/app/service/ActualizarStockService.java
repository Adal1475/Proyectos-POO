package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ActualizarStockDto;
import pe.edu.uni.app.dto.ValidarStockDto;

@Service
public class ActualizarStockService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ValidarStockService valstockservice;
	
	public ActualizarStockDto actualizarStock(ActualizarStockDto dto) {
		ValidarStockDto dtov = new ValidarStockDto(dto.getIdtipo(), 0, dto.getIncremento());
		valstockservice.valStock(dtov);
		
		dto.setContador(dtov.getContador()+dto.getIncremento());
		
		String sql="Update Tipo set contador = contador - "+ dto.getIncremento();
		sql += " where idtipo = ?";
		jdbcTemplate.update(sql, dto.getIdtipo());
		
		return dto;
	}
}
