package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ValidarIDPubDto;

@Service
public class ValidarIDPubService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public ValidarIDPubDto valIDPub(ValidarIDPubDto dto) {
		
		//Validar ID Publicación
		String sql = "select count(1) idpublicacion from PUBLICACION ";
		sql += "Where idpublicacion = ?";
		int i = jdbctemplate.queryForObject(sql,Integer.class,dto.getIdpublicacion());
		if(i == 0) {
			throw new RuntimeException("El ID de publicación " + dto.getIdpublicacion() + " no existe.");
		}
		
		return dto;
	}
}

