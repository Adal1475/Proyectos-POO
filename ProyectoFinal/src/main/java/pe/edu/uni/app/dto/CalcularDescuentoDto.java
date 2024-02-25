package pe.edu.uni.app.dto;

public class CalcularDescuentoDto {
	private float descuento;
	private int cantventa;
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public int getCantventa() {
		return cantventa;
	}
	public void setCantventa(int cantventa) {
		this.cantventa = cantventa;
	}
	public CalcularDescuentoDto(float descuento, int cantventa) {
		super();
		this.descuento = descuento;
		this.cantventa = cantventa;
	}
	public CalcularDescuentoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
