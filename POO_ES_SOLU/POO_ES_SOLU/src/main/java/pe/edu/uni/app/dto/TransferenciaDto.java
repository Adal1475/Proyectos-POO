package pe.edu.uni.app.dto;

public class TransferenciaDto {
	private String codcuentaorigen;
	private String codcuentadestino;
	private float importe;
	private String codempleado;
	
	public String getCodcuentaorigen() {
		return codcuentaorigen;
	}
	public void setCodcuentaorigen(String codcuentaorigen) {
		this.codcuentaorigen = codcuentaorigen;
	}
	public String getCodcuentadestino() {
		return codcuentadestino;
	}
	public void setCodcuentadestino(String codcuentadestino) {
		this.codcuentadestino = codcuentadestino;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public TransferenciaDto(String codcuentaorigen, String codcuentadestino, float importe, String codempleado) {
		super();
		this.codcuentaorigen = codcuentaorigen;
		this.codcuentadestino = codcuentadestino;
		this.importe = importe;
		this.codempleado = codempleado;
	}
	public TransferenciaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodempleado() {
		return codempleado;
	}
	public void setCodempleado(String codempleado) {
		this.codempleado = codempleado;
	}
	
	
}
