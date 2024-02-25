package pe.edu.uni.app.dto;

public class RegistrarPagoDto {
	private String alumno;
	private String curso;
	private float importe;
	private int cuota;
	private int emp_id;
	
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
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public RegistrarPagoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCuota() {
		return cuota;
	}
	public void setCuota(int cuota) {
		this.cuota = cuota;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public RegistrarPagoDto(String alumno, String curso, float importe, int cuota, int emp_id) {
		super();
		this.alumno = alumno;
		this.curso = curso;
		this.importe = importe;
		this.cuota = cuota;
		this.emp_id = emp_id;
	}
	
	
}
