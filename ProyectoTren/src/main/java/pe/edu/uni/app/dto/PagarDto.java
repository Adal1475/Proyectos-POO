package pe.edu.uni.app.dto;

public class PagarDto {
	
	private String tarjeta;
	private String estacion; //codigo de la estacion en la que ocurre el mov
	private String empleado; //codigo del empleado que hace el mov
	private String cliente; //cliente que paga
	
	
	
	public PagarDto() {
		super();
	}
	public PagarDto(String tarjeta, String estacion, String empleado, String cliente) {
		super();
		this.tarjeta = tarjeta;
		this.estacion = estacion;
		this.empleado = empleado;
		this.cliente = cliente;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	
}
