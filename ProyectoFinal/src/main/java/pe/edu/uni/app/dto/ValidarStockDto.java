package pe.edu.uni.app.dto;

public class ValidarStockDto {
	private String idtipo;
	private int contador;
	private int cantventa;

	public ValidarStockDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ValidarStockDto(String idtipo, int contador, int incremento) {
		super();
		this.idtipo = idtipo;
		this.contador = contador;
		this.cantventa = incremento;
	}

	public String getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(String idtipo) {
		this.idtipo = idtipo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public int getIncremento() {
		return cantventa;
	}

	public void setIncremento(int incremento) {
		this.cantventa = incremento;
	}
	
}
