package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ObtenerEmpleadosService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String , Object>> getEmpleados(){
		String sql= "Select chr_emplcodigo codigo, vch_emplpaterno paterno,";
		sql += "vch_emplmaterno materno, vch_emplnombre nombre, ";
		sql += "chr_empldni dni, vch_emplciudad ciudad,";
		sql	+= "vch_empldireccion direccion, vch_emplusuario usuario,";
		sql += "vch_emplclave clave";
		sql += " from Empleado";
		return jdbcTemplate.queryForList(sql);
	}
}
