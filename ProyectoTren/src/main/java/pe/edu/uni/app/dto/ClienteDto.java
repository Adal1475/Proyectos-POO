package pe.edu.uni.app.dto;

public class ClienteDto {
	
	private String paterno;
	private String materno;
	private String nombre;
	private String dni;
	private String ciudad;
	private String direccion;
	private String telefono;
	private String email;
	private String empleado;
	
	public ClienteDto() {
		super();
	}
	
	public ClienteDto(String paterno, String materno, String nombre, String dni, String ciudad,
			String direccion, String telefono, String email, String empleado) {
		super();
		this.paterno = paterno;
		this.materno = materno;
		this.nombre = nombre;
		this.dni = dni;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.empleado = empleado;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
