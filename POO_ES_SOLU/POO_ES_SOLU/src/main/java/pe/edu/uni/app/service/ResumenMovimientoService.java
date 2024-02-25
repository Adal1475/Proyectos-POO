package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResumenMovimientoService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Map<String,Object>> presentarResumen() {
		
		String sql = "select Movimiento.chr_tipocodigo as IDTIPO, ";
		sql +=  "(select vch_tipodescripcion from TipoMovimiento where chr_tipocodigo = Movimiento.chr_tipocodigo) as DESCRIPCION, ";
		sql += "(select vch_tipoaccion from TipoMovimiento where chr_tipocodigo = Movimiento.chr_tipocodigo) as ACCION, ";
		sql += "sum(case when (Movimiento.chr_cuencodigo = Cuenta.chr_cuencodigo and Cuenta.chr_monecodigo = 1) then Movimiento.dec_moviimporte else 0 end) as SOLES, ";
		sql += "sum(case when (Movimiento.chr_cuencodigo = Cuenta.chr_cuencodigo and Cuenta.chr_monecodigo = 2 ) then Movimiento.dec_moviimporte else 0 end) as DOLARES ";
		sql += "from Movimiento, Cuenta ";
		sql += "group by chr_tipocodigo";
		
		return jdbctemplate.queryForList(sql);
	}
}
