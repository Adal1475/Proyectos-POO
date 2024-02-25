package pe.edu.uni.app.dto;

public class ActualizarStockDto {
	private String idtipo;
	private int cantventa;
	private int contador;
	
	public String getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(String idtipo) {
		this.idtipo = idtipo;
	}
	public int getIncremento() {
		return cantventa;
	}
	public void setIncremento(int incremento) {
		this.cantventa = incremento;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public ActualizarStockDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ActualizarStockDto(String idtipo, int incremento, int contador) {
		super();
		this.idtipo = idtipo;
		this.cantventa = incremento;
		this.contador = contador;
	}
	
	
}
