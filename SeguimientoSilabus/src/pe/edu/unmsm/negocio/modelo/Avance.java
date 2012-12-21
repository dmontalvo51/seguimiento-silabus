package pe.edu.unmsm.negocio.modelo;

import java.io.Serializable;

public class Avance  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoFichaAvance;
	private String codigoSilabus;
	private int nroSemana;
	private int nroActividad;
	private String detalleActividad;
	private String observaciones;
	private String tarea;
	private String fechaRegistrada;
	private String fecha;
	private String hora;
	
	
	
	public Avance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigoFichaAvance() {
		return codigoFichaAvance;
	}
	public void setCodigoFichaAvance(String codigoFichaAvance) {
		this.codigoFichaAvance = codigoFichaAvance;
	}
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFechaRegistrada() {
		return fechaRegistrada;
	}
	public void setFechaRegistrada(String fechaRegistrada) {
		this.fechaRegistrada = fechaRegistrada;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getDetalleActividad() {
		return detalleActividad;
	}
	public void setDetalleActividad(String detalleActividad) {
		this.detalleActividad = detalleActividad;
	} 

}
