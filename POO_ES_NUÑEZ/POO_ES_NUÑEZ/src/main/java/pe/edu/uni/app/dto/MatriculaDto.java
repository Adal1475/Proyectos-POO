package pe.edu.uni.app.dto;

public class MatriculaDto {
	private int idcursoprog;
	private String apealumno;
	private String nomalumno;
	private String seccion;
	private String ciclo;

	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	
	
	public MatriculaDto(int idcursoprog, String apealumno, String nomalumno, String seccion, String ciclo) {
		super();
		this.setIdcursoprog(idcursoprog);
		this.apealumno = apealumno;
		this.nomalumno = nomalumno;
		this.seccion = seccion;
		this.ciclo = ciclo;
	}
	public MatriculaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getApealumno() {
		return apealumno;
	}
	public void setApealumno(String apealumno) {
		this.apealumno = apealumno;
	}
	public String getNomalumno() {
		return nomalumno;
	}
	public void setNomalumno(String nomalumno) {
		this.nomalumno = nomalumno;
	}
	public int getIdcursoprog() {
		return idcursoprog;
	}
	public void setIdcursoprog(int idcursoprog) {
		this.idcursoprog = idcursoprog;
	}
	
	
}
