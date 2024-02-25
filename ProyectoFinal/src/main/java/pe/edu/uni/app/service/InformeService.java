package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InformeService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String presentarInforme() {
		String sql = "select idpublicacion as IDTIPO, "
				+ "(select titulo from PUBLICACION where idpublicacion = venta.idpublicacion) as DESCRIPCION,"
				+ "sum(cantidad) as CANTIDAD_TOTAL,"
				+ "sum(total) as VENTAS_TOTAL "
				+ "from venta "
				+ "group by idpublicacion";
		List<Map<String, Object>> lista = jdbcTemplate.queryForList(sql);
		String resumen = lista.toString();
		return resumen;
	}
}
