package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.RegistrarPagoDto;

@Service
public class RegistrarPagoService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public RegistrarPagoDto registrarPago(RegistrarPagoDto dto) {
		//Validar matricula
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
				
		//Validar cuota
				//Obtener cuotas
				sql = "select mat_cuotas from MATRICULA ";
				sql		+= "where cur_id = ? and alu_id = ?";
				int mat_cuotas = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
				if(dto.getCuota()>mat_cuotas){
					throw new RuntimeException("La cuota ingresada es superior al número de cuotas.");
				}
				
				//Obtener precio
				sql = "select cur_precio from CURSO ";
				sql +=	"where cur_id = ?";
				float precio = jdbctemplate.queryForObject(sql, Float.class, cur_id);
				
				//Obtener tipo
				sql = "select mat_tipo from MATRICULA ";
				sql		+= "where cur_id = ? and alu_id = ?";
				String mat_tipo = jdbctemplate.queryForObject(sql, String.class, cur_id, alu_id);
				
				float descuento=1;
				int cantmaxcuotas = 0;
				int j,k;
				
				sql = "select count(1) mat_tipo from MATRICULA ";
				sql		+= "where cur_id = ? and alu_id = ? and mat_tipo = 'REGULAR'";
				i = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
				
				sql = "select count(1) mat_tipo from MATRICULA ";
				sql		+= "where cur_id = ? and alu_id = ? and mat_tipo = 'SEMIBECA'";
				j = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
				
				sql = "select count(1) mat_tipo from MATRICULA ";
				sql		+= "where cur_id = ? and alu_id = ? and mat_tipo = 'BECA'";
				k = jdbctemplate.queryForObject(sql, Integer.class, cur_id, alu_id);
				
				if(i == 1) {
					cantmaxcuotas = 3;
					descuento = 1;
				}
				else if(j == 1) {
					cantmaxcuotas = 1;
					descuento = (float) 0.5;
				}
				else if (k == 1){
					cantmaxcuotas = 0;
					descuento = 0;
				} 
				
				if(cantmaxcuotas > dto.getCuota()) {
					throw new RuntimeException("El tipo de matrícula no permite ese número de cuota.");
				}
				
				
				//Obtener el número de cuotas ya pagadas
				sql = "select count(?) pag_cuota from PAGO ";
				sql +=	"where cur_id = ? and alu_id = ?";
				int cuotas_pagadas = jdbctemplate.queryForObject(sql, Integer.class, mat_cuotas, cur_id, alu_id);
				if(!(cuotas_pagadas == dto.getCuota()-1)){
					throw new RuntimeException("No corresponde pagar el número de cuota ingresado.");
				}
				
				
		//Validar Importe
		if (mat_cuotas == 1) {
			if(precio != dto.getImporte()) {
			throw new RuntimeException("El importe ingresado es incorrecto. Se debe pagar el monto exacto.");
			}
			}
		else if(mat_cuotas == 2) {
			if (precio != 2*dto.getImporte()) {
				throw new RuntimeException("El importe ingresado es incorrecto. Se debe pagar la mitad del monto.");
			}
		}
		else if(mat_cuotas == 3) {
			if(dto.getCuota() == 1) {
				if(precio*0.4 != dto.getImporte()) {
					throw new RuntimeException("El importe ingresado es incorrecto. Se debe pagar el 40%.");
				}
			}
			if(dto.getCuota() >= 2) {
				if(precio*0.3 != dto.getImporte()) {
					throw new RuntimeException("El importe ingresado es incorrecto. Se debe pagar el 30%.");
				}
			}
		}
		
		//Registrar pago
		sql = "insert into PAGO ";
		sql += "values (?,?, ?, ?, getdate(),?)";
		jdbctemplate.update(sql, cur_id, alu_id, dto.getCuota(), dto.getEmp_id(), dto.getImporte());	
		
				
		return dto;
	}
}
