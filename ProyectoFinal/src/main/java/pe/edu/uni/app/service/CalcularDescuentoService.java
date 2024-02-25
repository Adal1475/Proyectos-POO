package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.CalcularDescuentoDto;

@Service
public class CalcularDescuentoService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CalcularDescuentoDto calcularDescuento(CalcularDescuentoDto dto) {
		if(dto.getCantventa()<=0) {
			throw new RuntimeException("Error: La cantidad vendida debe ser mayor a 0.");
		}
		
		float descuento;
		if(dto.getCantventa()>10000) {
			descuento = (float) 0.2;
			dto.setDescuento(descuento);
			throw new RuntimeException("El descuento es " + dto.getDescuento() + " (La cantidad vendida superó a 10000 unidades)");
		}
		else {
			String sql = "select porcentaje from PROMOCION ";
			sql += "where cantmin <= ? and cantmax >= ?";
			descuento = jdbcTemplate.queryForObject(sql,Float.class,dto.getCantventa(),dto.getCantventa());
			dto.setDescuento(descuento);
		}
		
		return dto;
	}
}
