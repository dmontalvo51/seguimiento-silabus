package pe.edu.unmsm.negocio.modelo;


public class Respuesta {
	public static int OK=1;
	public static int ERROR=2;
	
	private int estado;
	private String mensaje;
	private String mensaje2;
	private Object dato;
	
	
	public Object getDato() {
		return dato;
	}
	public void setDato(Object dato) {
		this.dato = dato;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getMensaje2() {
		return mensaje2;
	}
	public void setMensaje2(String mensaje2) {
		this.mensaje2 = mensaje2;
	}
	
	
}
