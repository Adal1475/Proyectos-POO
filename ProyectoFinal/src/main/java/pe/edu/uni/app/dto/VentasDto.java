package pe.edu.uni.app.dto;

public class VentasDto {
	private String idpublicacion;
	private String descpublicacion;
	private String empleusuario;
	private String emplecontra;
	private String emplenombre;
	private float descuento;
	private float precioventa;
	private float subtotal;
	private float impuesto;
	private float total;
	private int cantventa;
	private int idventa;
	private int idempleado;
	private String cliente;
	
	public String getIdpublicacion() {
		return idpublicacion;
	}
	public void setIdpublicacion(String idpublicacion) {
		this.idpublicacion = idpublicacion;
	}
	public int getCantventa() {
		return cantventa;
	}
	public void setCantventa(int cantventa) {
		this.cantventa = cantventa;
	}
	public VentasDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public float getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
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
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getEmplecontra() {
		return emplecontra;
	}
	public void setEmplecontra(String emplecontra) {
		this.emplecontra = emplecontra;
	}
	public String getEmpleusuario() {
		return empleusuario;
	}
	public void setEmpleusuario(String empleusuario) {
		this.empleusuario = empleusuario;
	}
	public int getIdempleado() {
		return idempleado;
	}
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}
	public String getEmplenombre() {
		return emplenombre;
	}
	public void setEmplenombre(String emplenombre) {
		this.emplenombre = emplenombre;
	}
	public String getDescpublicacion() {
		return descpublicacion;
	}
	public void setDescpublicacion(String descpublicacion) {
		this.descpublicacion = descpublicacion;
	}
	
}
