package pe.edu.uni.app.dto;

public class ReporteDto {
	private String alumno;
	private String curso;
	private String tipo;
	private float precio;
	private int cuotas;
	private float nota;
	private int alu_id;
	private String[] pagos;
	
	
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public ReporteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAlu_id() {
		return alu_id;
	}
	public void setAlu_id(int alu_id) {
		this.alu_id = alu_id;
	}
	public String[] getPagos() {
		return pagos;
	}
	public void setPagos(String[] pagos) {
		this.pagos = pagos;
	}
	
	
}
