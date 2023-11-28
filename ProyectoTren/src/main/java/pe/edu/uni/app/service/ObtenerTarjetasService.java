package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ObtenerTarjetasService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> getTarjates(){
		String sql = "select chr_tarjcodigo codigo, chr_tarjtipo tipo,";
		sql += "chr_estacodigo estacion, chr_emplcreatarjeta empleado,";
		sql += "chr_cliecodigo cliente, dec_tarjsaldo saldo,";
		sql += "dtt_tarjfechacreacion fecha ";
		sql += "from Tarjeta";
		return jdbcTemplate.queryForList(sql);
	}
	
}

