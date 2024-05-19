package pe.edu.uni.app.dto;

public class CambiarTarifaDto {
	
	private String tipo; //codigo del tipo de tarjeta
	private	float nuevaTarifa;
	private String empleado;
	
	public CambiarTarifaDto() {
		super();
	}
	public CambiarTarifaDto(String tipo, float nuevaTarifa, String empleado) {
		super();
		this.tipo = tipo;
		this.nuevaTarifa = nuevaTarifa;
		this.empleado = empleado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getNuevaTarifa() {
		return nuevaTarifa;
	}
	public void setNuevaTarifa(float nuevaTarifa) {
		this.nuevaTarifa = nuevaTarifa;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	
	
}
