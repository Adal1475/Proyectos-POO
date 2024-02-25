package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.MatriculaDto;

@Service
public class MatriculaService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public MatriculaDto matricular(MatriculaDto dto, int proceso) {
		//Verificar si el alumno existe
		String sql = "select count(1) IdAlumno from Alumno where ApeAlumno = ? and NomAlumno = ?";
		int i = jdbctemplate.queryForObject(sql, Integer.class, dto.getApealumno(), dto.getNomalumno());
		if (i == 0) {
			throw new RuntimeException("El alumno no existe en la base de datos.");
		}
		
		//Obtener idalumno
		sql = "select IdAlumno from Alumno where ApeAlumno = ? and NomAlumno = ?";
		String idalumno = jdbctemplate.queryForObject(sql, String.class, dto.getApealumno(), dto.getNomalumno());
		
		//VErificar si ya está matriculado
		sql = "select count(1) IdCursoProg from Matricula where IdCursoProg = ? and IdAlumno = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getIdcursoprog(), idalumno);
		if (i != 0) {
			throw new RuntimeException("El alumno ya está matriculado en el curso.");
		}
		
		if(proceso < 2) {
			return dto;
		}
		
		//Curso activo
		sql = "select Activo from CursoProgramado where IdCursoProg = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getIdcursoprog());
		if (i == 0) {
			throw new RuntimeException("El curso no está activo");
		}
		
		if(proceso < 3) {
			return dto;
		}
		
		//Verificar vacantes
		sql = "select vacantes from CursoProgramado where IdCursoProg = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getIdcursoprog());
		if (i == 0) {
			throw new RuntimeException("El curso no posee vacantes.");
		}
		
		if(proceso < 4) {
			return dto;
		}
		
		//Verificar ciclo
		sql = "select count(1) idcursoprog from CursoProgramado where IdCursoProg = ? and IdCiclo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getIdcursoprog());
		if (i == 0) {
			throw new RuntimeException("El curso no está programado en el ciclo correspondiente.");
		}
		
		if(proceso < 5) {
			return dto;
		}
		
		//Cambiar vacantes y matriculados
		sql = "update CursoProgramado set Vacantes = Vacantes + 1 where IdCursoProg = ?";
		jdbctemplate.update(sql);
		
		sql = "update CursoProgramado set Matriculados = Matriculados-1 where IdCursoProg = ?";
		jdbctemplate.update(sql);
		
		if(proceso < 5) {
			return dto;
		}
		
		//MAtricular al alumno
		sql = "insert into Matricula values(?,?,GETDATE(),0,0,0,0,NULL)";
		jdbctemplate.update(sql,dto.getIdcursoprog(),idalumno);
		
		
		return dto;
	}
}
