package pe.edu.uni.app.dto;

import org.springframework.stereotype.Service;

@Service
public class EmpleadoDto {
	private String apellPaterno;
	private String apellMaterno;
	private String nombre;
	private String dni;
	private String ciudad;
	private String estacion;
	private String direccion;
	private String usuario;
	private String clave;
	
	public EmpleadoDto() {
		super();
	}
	public EmpleadoDto(String apellPaterno, String apellMaterno, String nombre, String dni,
			String ciudad, String estacion, String direccion, String usuario, String clave) {
		super();
		this.apellPaterno = apellPaterno;
		this.apellMaterno = apellMaterno;
		this.nombre = nombre;
		this.dni = dni;
		this.ciudad = ciudad;
		this.estacion = estacion;
		this.direccion = direccion;
		this.usuario = usuario;
		this.clave = clave;
	}
	public String getApellPaterno() {
		return apellPaterno;
	}
	public void setApellPaterno(String apellPaterno) {
		this.apellPaterno = apellPaterno;
	}
	public String getApellMaterno() {
		return apellMaterno;
	}
	public void setApellMaterno(String apellMaterno) {
		this.apellMaterno = apellMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
