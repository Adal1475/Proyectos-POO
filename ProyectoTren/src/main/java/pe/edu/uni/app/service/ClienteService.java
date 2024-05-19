package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ClienteDto;

@Service
public class ClienteService {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public ClienteDto crearCliente(ClienteDto dto) {
		//Validar DNI
		String sql = "select count(1) cont from Cliente "; 
		sql += "Where chr_cliedni = ?";
		int i = jdbctemplate.queryForObject(sql,Integer.class,dto.getDni());
		if(i == 1) {
			throw new RuntimeException("Cliente ya registrado con el dni ingresado.");
		}
		long dni = Long.parseLong(dto.getDni());
		if(dni < 10000000 || dni >= 100000000) {
			throw new RuntimeException("Dni ingresado incorrecto");
		}
		
		//Validar Apellidos y Nombres
		sql = "Select count(1) cont from Cliente ";
		sql += "Where vch_cliepaterno = ? and vch_cliematerno = ? and vch_clienombre = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class,dto.getApellPaterno(),dto.getApellMaterno(),dto.getNombre());
		if(i == 1) {
			throw new RuntimeException("Empleado ya registrado con los apellidos y nombres ingresado");
		}
		//Validar Empleado
		sql= "Select count(1) filas from Empleado ";
		sql+="where chr_emplcodigo=?";
		int filas= jdbctemplate.queryForObject(sql, Integer.class, dto.getEmpleado());
		if (filas==0) {
			throw new RuntimeException("Codigo del empleado incorrecto.");
		}
				
		//Recuperar el Contador de Clientes
		sql = "Update Contador set int_contitem = int_contitem + 1 ";
		sql += "Where vchr_conttabla = 'Cliente'";
		jdbctemplate.update(sql);
		sql = "Select int_contitem from Contador ";
		sql += "Where vchr_conttabla = 'Cliente'";
		int cont = jdbctemplate.queryForObject(sql, Integer.class);
				
		//Crear el Codigo del Cliente
		String formato = "%05d";
		String clieCodigo = String.format(formato, cont);
				
		//Registrar en la Tabla Clientes
		sql = "Insert into Cliente ";
		sql += "Values (?,?,?,?,?,?,?,?,?)";
		jdbctemplate.update(sql,clieCodigo,dto.getApellPaterno(),dto.getApellMaterno(),dto.getNombre(),dto.getDni(),dto.getCiudad(),dto.getDireccion(),dto.getTelefono(),dto.getEmail());

		//Registrar en la Tabla Movimiento
		sql="select count(*) filas from Movimiento where chr_tipocodigo= '006'";
		filas= jdbctemplate.queryForObject(sql, Integer.class);
		filas++;
		sql = "Insert into Movimiento ";
		sql += "Values (NULL,?,getdate(),?,?,'006')";
		jdbctemplate.update(sql,filas,clieCodigo,dto.getEmpleado());
		
		return dto;
	}
}
