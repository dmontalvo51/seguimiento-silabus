package pe.edu.unmsm.presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.unmsm.negocio.modelo.Usuario;


@SessionScoped
@ManagedBean(name="usuario")
public class UsuarioController {

	Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
