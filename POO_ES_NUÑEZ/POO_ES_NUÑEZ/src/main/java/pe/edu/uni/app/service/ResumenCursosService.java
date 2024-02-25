package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ResumenCursosDto;

@Service
public class ResumenCursosService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Map<String, Object>> presentarResumen(ResumenCursosDto dto) {
		String sql = "select IdCurso as IDCURSO, "
				+ "(select NomCurso from Curso where IdCurso = CursoProgramado.IdCurso) as NOMBRE, "
				+ "Horario as SECCIONES, "
				+ "Vacantes as PROGRAMADOS, "
				+ "Matriculados as MATRICULADOS, "
				+ "(Matriculados * PreCursoProg) as INGRESOS "
				+ "from CursoProgramado where IdCiclo = ?";
		return(jdbctemplate.queryForList(sql,dto.getCiclo()));
	}
}
