package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.EmpleadoDto;

@Service
public class EmpleadoService {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public EmpleadoDto crearEmpleado(EmpleadoDto dto) {
		
		//Validar DNI
		String sql = "select count(1) cont from Empleado "; 
		sql += "Where chr_empldni = ?";
		int i = jdbctemplate.queryForObject(sql,Integer.class,dto.getDni());
		if(i == 1) {
			throw new RuntimeException("Empleado ya registrado con el dni ingresado.");
		}
		long dni = Long.parseLong(dto.getDni());
		if(dni < 10000000 || dni >= 100000000) {
			throw new RuntimeException("Dni ingresado incorrecto");
		}
		//Validar Apellidos y Nombres
		sql = "Select count(1) cont from Empleado ";
		sql += "Where vch_emplpaterno = ? and vch_emplmaterno = ? and vch_emplnombre = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class,dto.getApellPaterno(),dto.getApellMaterno(),dto.getNombre());
		if(i == 1) {
			throw new RuntimeException("Empleado ya registrado con los apellidos y nombres ingresado");
		}
		
		//Validar Estacion
		sql = "Select count(1) cont from Estacion ";
		sql += "Where vchr_estanombre = ?";
		i = jdbctemplate.queryForObject(sql,Integer.class,dto.getEstacion());
		if(i == 0) {
			throw new RuntimeException("Nombre de estacion incorrecto.");
		}
		
		//Validar Usuario
		sql = "Select count(1) cont from Empleado ";
		sql += "Where vch_emplusuario = ?";
		i = jdbctemplate.queryForObject(sql,Integer.class,dto.getUsuario());
		if(i == 1) {
			throw new RuntimeException("Nombre de usuario en uso, eliga otro.");
		}

		//Recuperar el contador para empleados y asignados
		String string = "Empleado";
		sql = "Update Contador set int_contitem = int_contitem + 1 "; 
		sql += "Where vchr_conttabla = ?";
		jdbctemplate.update(sql,string);
		string = "Asignado";
		sql = "Update Contador set int_contitem = int_contitem + 1 ";
		sql += "Where vchr_conttabla = ?";
		jdbctemplate.update(sql,string);
		sql = "Select int_contitem from Contador ";
		sql += "Where vchr_conttabla = ?";
		int cont = jdbctemplate.queryForObject(sql,Integer.class,string);

		//Recuperar el Codigo de la Estacion
		sql = "Select chr_estacodigo from Estacion ";
		sql += "Where vchr_estanombre = ?";
		String estaCodigo = jdbctemplate.queryForObject(sql,String.class,dto.getEstacion());
		
		//Creando el Codigo del Empleado y Asignado
		String formato = "%04d";
		String emplCodigo = String.format(formato,cont);
		formato = "%06d";
		String asigCodigo = String.format(formato,cont);
		
		//Registrar en Tabla Asignados
		sql = "Insert into Asignado ";
		sql += "Values (?,?,?)";
		jdbctemplate.update(sql,asigCodigo,estaCodigo,emplCodigo);
				
		//Registrar en la Tabla Empleados
		sql = "Insert into Empleado ";
		sql += "Values (?,?,?,?,?,?,?,?,?)";
		jdbctemplate.update(sql,emplCodigo,dto.getApellPaterno(),dto.getApellMaterno(),dto.getNombre(),dto.getDni(),dto.getCiudad(),dto.getDireccion(),dto.getUsuario(),dto.getClave());		
		
		//Registrar movimiento
		sql="select count(*) filas from Movimiento where chr_tipocodigo= '007'";
		int filas= jdbctemplate.queryForObject(sql, Integer.class);
		filas++;
		sql = "Insert into Movimiento ";
		sql += "Values (NULL,?,getdate(),NULL,?,'007')";
		jdbctemplate.update(sql,filas,emplCodigo);
		
		return dto;
	}
}
