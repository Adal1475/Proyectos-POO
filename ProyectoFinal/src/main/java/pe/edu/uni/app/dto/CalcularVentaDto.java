package pe.edu.uni.app.dto;

public class CalcularVentaDto {
	private String idpublicacion;
	private float precioventa;
	private int cantventa;
	private float descuento;
	private float subtotal;
	private float impuesto;
	private float total;
	
	public float getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public float getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public CalcularVentaDto(String idpublicacion, float precioventa, int cantventa, float descuento, float subtotal, float impuesto, float total) {
		super();
		this.idpublicacion = idpublicacion;
		this.precioventa = precioventa;
		this.cantventa = cantventa;
		this.descuento = descuento;
		this.subtotal = subtotal;
		this.impuesto = impuesto;
		this.total = total;
	}
	public CalcularVentaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCantventa() {
		return cantventa;
	}
	public void setCantventa(int cantventa) {
		this.cantventa = cantventa;
	}
	public String getIdpublicacion() {
		return idpublicacion;
	}
	public void setIdpublicacion(String idpublicacion) {
		this.idpublicacion = idpublicacion;
	}
	
	
}
