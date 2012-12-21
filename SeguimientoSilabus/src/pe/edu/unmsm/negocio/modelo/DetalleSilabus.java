package pe.edu.unmsm.negocio.modelo;

import java.io.Serializable;

public class DetalleSilabus implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String codigoSilabus;
	private int nroSemana;
	private int nroActividad;
	private String detalle;
	
	
	
	public String getCodigoSilabus() {
		return codigoSilabus;
	}
	public void setCodigoSilabus(String codigoSilabus) {
		this.codigoSilabus = codigoSilabus;
	}
	public int getNroSemana() {
		return nroSemana;
	}
	public void setNroSemana(int nroSemana) {
		this.nroSemana = nroSemana;
	}
	public int getNroActividad() {
		return nroActividad;
	}
	public void setNroActividad(int nroActividad) {
		this.nroActividad = nroActividad;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	

}
