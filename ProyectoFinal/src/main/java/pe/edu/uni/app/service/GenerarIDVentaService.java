package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.GenerarIDVentaDto;

@Service
public class GenerarIDVentaService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public GenerarIDVentaDto generarIDVenta(GenerarIDVentaDto dto) {
		String sql="Update CONTROL set valor = valor + 1 ";
		sql += " where parametro = 'VENTA'";
		jdbcTemplate.update(sql);
		
		sql = "select valor from CONTROL ";
	    sql += "where parametro = 'VENTA'";
	    int idventa = jdbcTemplate.queryForObject(sql,Integer.class);
	    dto.setIdventa(idventa);
		return dto;
	}
}
