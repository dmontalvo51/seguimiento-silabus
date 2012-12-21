package pe.edu.unmsm.negocio.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 5735379635324594498L;
	
	private String cuenta;
	private String nombres;
	private String aPaterno;
	private String aMaterno;
	private int tipo;
	private String tipoUsuario;
	private Date fecha;
	private String correo;
	
	private List<OpcionMenu> opcionesMenu;
	
	
	public List<OpcionMenu> getOpcionesMenu() {
		return opcionesMenu;
	}
	public void setOpcionesMenu(List<OpcionMenu> opcionesMenu) {
		this.opcionesMenu = opcionesMenu;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	


}
