package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ReporteDto;

@Service
public class ReporteService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public ReporteDto presentarReporte(ReporteDto dto) {
		//Comprobar si el alumno existe:
		String sql = "select count(1) alu_id from ALUMNO ";
		sql += "where alu_nombre = ?";
		System.out.print(dto.getAlumno());
		int i = jdbctemplate.queryForObject(sql, Integer.class, dto.getAlumno());
		if(i == 0) {
			throw new RuntimeException("El nombre ingresado no existe.");
		}
		
		//Obtener id alumno
		sql = "select alu_id from ALUMNO";
		sql += " where alu_nombre = ?";
		int alu_id = jdbctemplate.queryForObject(sql, Integer.class, dto.getAlumno());
		dto.setAlu_id(alu_id);
		
		//Comprobar si existe el curso
		sql = "select count(1) cur_id from CURSO ";
		sql +=		 "where cur_nombre = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getCurso());
		if(i == 0) {
			throw new RuntimeException("El curso ingresado no existe.");
		}
		
		//Obtener id curso
		sql = "select cur_id from CURSO ";
		sql		+= "where cur_nombre = ?";
		int cur_id = jdbctemplate.queryForObject(sql, Integer.class, dto.getCurso());
		
		//Comprobar si el alumno está matriculado en el curso
		sql = "select count(1) alu_id from MATRICULA ";
		sql		+= "where cur_id = ? and alu_id = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
		if(i == 0) {
			throw new RuntimeException("El curso ingresado no existe.");
		}		
		
		//Obtener precio
		sql = "select cur_precio from CURSO ";
		sql +=	"where cur_id = ?";
		float precio = jdbctemplate.queryForObject(sql, Float.class, cur_id);
		dto.setPrecio(precio);
		
		//Obtener tipo
		sql = "select mat_tipo from MATRICULA ";
		sql		+= "where cur_id = ? and alu_id = ?";
		String mat_tipo = jdbctemplate.queryForObject(sql, String.class, cur_id, alu_id);
		dto.setTipo(mat_tipo);
		
		//Obtener cuotas
		sql = "select mat_cuotas from MATRICULA ";
		sql		+= "where cur_id = ? and alu_id = ?";
		int mat_cuotas = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
		
		//Obtener nota
		sql = "select mat_nota from MATRICULA ";
		sql	+= "where cur_id = ? and alu_id = ?";
		int mat_nota = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
		dto.setNota(mat_nota);
		
		String pagos[] = new String[mat_cuotas];
		//Obtener pagos
		for(i = 1; i <= mat_cuotas; i++) {
			sql = "select pag_fecha from pago ";
			sql	+= "where cur_id = ? and alu_id = ? and pag_cuota = ?";
			String pag_fecha = jdbctemplate.queryForObject(sql, String.class, cur_id, alu_id, i);
			
			sql = "select pag_importe from pago ";
			sql	+= "where cur_id = ? and alu_id = ? and pag_cuota = ?";
			float pag_importe = jdbctemplate.queryForObject(sql, Float.class, cur_id, alu_id, i);
			
			pagos[i-1] = " " + i + " " + pag_fecha + "" + pag_importe;
		}
		dto.setPagos(pagos);
		
		return dto;
	}
}
