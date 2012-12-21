package pe.edu.unmsm.negocio.modelo;

public class Silabus {
	
	private String codigoSilabus;
	private String codigoCurso;
	private String curso;
	private int plan;
	private String estado;
	private String ruta;
	private String fecha;
	private String hora;
	
	public Silabus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Silabus(String codigoSilabus, String codigoCurso, String curso,
			int plan, String estado, String ruta) {
		super();
		this.codigoSilabus = codigoSilabus;
		this.codigoCurso = codigoCurso;
		this.curso = curso;
		this.plan = plan;
		this.estado = estado;
		this.ruta = ruta;
	}


	public String getCodigoSilabus() {
		return codigoSilabus;
	}
	public void setCodigoSilabus(String codigoSilabus) {
		this.codigoSilabus = codigoSilabus;
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}
	
	

}
