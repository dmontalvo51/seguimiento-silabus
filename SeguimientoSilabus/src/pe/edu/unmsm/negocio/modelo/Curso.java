package pe.edu.unmsm.negocio.modelo;

import java.io.Serializable;

public class Curso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codigo;
	private int plan;
	private String descripcion;
	private int creditos;
	private int tieneSilabus;
	private String estadoSilabus;
	private String codigoSilabus;
	
		
	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Curso(String codigo, int plan, String descripcion, int creditos) {
		super();
		this.codigo = codigo;
		this.plan = plan;
		this.descripcion = descripcion;
		this.creditos = creditos;
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}


	public int getTieneSilabus() {
		return tieneSilabus;
	}


	public void setTieneSilabus(int tieneSilabus) {
		this.tieneSilabus = tieneSilabus;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEstadoSilabus() {
		return estadoSilabus;
	}


	public void setEstadoSilabus(String estadoSilabus) {
		this.estadoSilabus = estadoSilabus;
	}


	public String getCodigoSilabus() {
		return codigoSilabus;
	}


	public void setCodigoSilabus(String codigoSilabus) {
		this.codigoSilabus = codigoSilabus;
	}


}
