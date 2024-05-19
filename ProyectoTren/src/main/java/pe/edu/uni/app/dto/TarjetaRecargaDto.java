package pe.edu.uni.app.dto;

public class TarjetaRecargaDto {

	private String tarjeta;
	private float dineroRecarga;
	private String estacion; //codigo de la estacion en la que se esta haciendo la recarga
	private String empleado; //codigo del empleado que hace la recarga
	private String cliente; //cliente que hace la recarga
	
	public TarjetaRecargaDto() {
		super();
	}
	
	public TarjetaRecargaDto(String tarjeta, float dineroRecarga, String estacion, String empleado,
			String cliente) {
		super();
		this.tarjeta = tarjeta;
		this.dineroRecarga = dineroRecarga;
		this.estacion = estacion;
		this.empleado = empleado;
		this.cliente = cliente;
	}


	public float getDineroRecarga() {
		return dineroRecarga;
	}

	public void setDineroRecarga(float dineroRecarga) {
		this.dineroRecarga = dineroRecarga;
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
