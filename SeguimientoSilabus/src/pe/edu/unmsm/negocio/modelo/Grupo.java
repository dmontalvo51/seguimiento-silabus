package pe.edu.unmsm.negocio.modelo;

import java.io.Serializable;

public class Grupo implements Serializable{
	
	private static final long serialVersionUID = -6651942395287264130L;
	
	private String nombreCurso;
	private String codigoCurso;
	private int plan;
	private int codigoGrupo;
	private int anoGrupo;
	private int cicloGrupo; //0,1,2
	
	private String codigoDelegado;
	private String nombreDelegado;
	private String codigoDocente;
	private String nombreDocente;
	
	private int aula;
	private int estadoGrupo;
	
	private String codigoSilabus;
	
	public Grupo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	public int getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(int codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	public int getCicloGrupo() {
		return cicloGrupo;
	}
	public void setCicloGrupo(int cicloGrupo) {
		this.cicloGrupo = cicloGrupo;
	}
	public String getCodigoDelegado() {
		return codigoDelegado;
	}
	public void setCodigoDelegado(String codigoDelegado) {
		this.codigoDelegado = codigoDelegado;
	}
	public String getNombreDelegado() {
		return nombreDelegado;
	}
	public void setNombreDelegado(String nombreDelegado) {
		this.nombreDelegado = nombreDelegado;
	}
	
	public int getAula() {
		return aula;
	}
	public void setAula(int aula) {
		this.aula = aula;
	}
	public int getEstadoGrupo() {
		return estadoGrupo;
	}
	public void setEstadoGrupo(int estadoGrupo) {
		this.estadoGrupo = estadoGrupo;
	}

	public int getAnoGrupo() {
		return anoGrupo;
	}

	public void setAnoGrupo(int anoGrupo) {
		this.anoGrupo = anoGrupo;
	}

	public String getCodigoSilabus() {
		return codigoSilabus;
	}

	public void setCodigoSilabus(String codigoSilabus) {
		this.codigoSilabus = codigoSilabus;
	}

	public String getCodigoDocente() {
		return codigoDocente;
	}

	public void setCodigoDocente(String codigoDocente) {
		this.codigoDocente = codigoDocente;
	}

	public String getNombreDocente() {
		return nombreDocente;
	}

	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}
	
	
	

}
