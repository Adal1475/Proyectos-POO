package pe.edu.uni.app.dto;

public class EliminarTarjetaDto {
	
	private String tarjeta;
	private String empleado;
	
	public EliminarTarjetaDto() {
		super();
	}
	public EliminarTarjetaDto(String tarjeta, String empleado) {
		super();
		this.tarjeta = tarjeta;
		this.empleado = empleado;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	
	

}
