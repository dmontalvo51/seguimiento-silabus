package pe.edu.unmsm.negocio.modelo;

import java.io.Serializable;

public class Ficha implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//datos de la tabla
	
	private String codigoFicha;
	private String estadoFicha;
	private int codigoEstadoFicha;
	private String ruta;
	private String codigoCurso;
	private int planCurso;
	private String nombreCurso;
	private int codigoGrupo;
	private int anoGrupo;
	private int cicloGrupo;
	private String codigoDocente;
	private String fecha;
	private String hora;
	
	
	public Ficha() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCodigoFicha() {
		return codigoFicha;
	}


	public void setCodigoFicha(String codigoFicha) {
		this.codigoFicha = codigoFicha;
	}


	public String getEstadoFicha() {
		return estadoFicha;
	}


	public void setEstadoFicha(String estadoFicha) {
		this.estadoFicha = estadoFicha;
	}


	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public String getCodigoCurso() {
		return codigoCurso;
	}


	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}


	public int getPlanCurso() {
		return planCurso;
	}


	public void setPlanCurso(int planCurso) {
		this.planCurso = planCurso;
	}


	public String getNombreCurso() {
		return nombreCurso;
	}


	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}


	public int getCodigoGrupo() {
		return codigoGrupo;
	}


	public void setCodigoGrupo(int codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}


	public int getAnoGrupo() {
		return anoGrupo;
	}


	public void setAnoGrupo(int anoGrupo) {
		this.anoGrupo = anoGrupo;
	}


	public int getCicloGrupo() {
		return cicloGrupo;
	}


	public void setCicloGrupo(int cicloGrupo) {
		this.cicloGrupo = cicloGrupo;
	}


	public String getCodigoDocente() {
		return codigoDocente;
	}


	public void setCodigoDocente(String codigoDocente) {
		this.codigoDocente = codigoDocente;
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


	public int getCodigoEstadoFicha() {
		return codigoEstadoFicha;
	}


	public void setCodigoEstadoFicha(int codigoEstadoFicha) {
		this.codigoEstadoFicha = codigoEstadoFicha;
	}
	
	
		


	
}
	