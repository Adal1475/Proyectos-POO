package pe.edu.uni.app.dto;

public class AdquirirTarjetaDto {
	
	private String codigo;
	private String empleado;
	private String cliente;
	private String tipo;
	
	public AdquirirTarjetaDto() {
		super();
	}
	public AdquirirTarjetaDto(String codigo, String empleado, String cliente, String tipo) {
		super();
		this.codigo = codigo;
		this.empleado = empleado;
		this.cliente = cliente;
		this.tipo = tipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
