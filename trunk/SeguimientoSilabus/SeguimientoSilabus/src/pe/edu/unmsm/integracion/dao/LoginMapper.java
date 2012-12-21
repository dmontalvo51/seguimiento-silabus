package pe.edu.unmsm.integracion.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import pe.edu.unmsm.negocio.modelo.OpcionMenu;
import pe.edu.unmsm.negocio.modelo.Usuario;


public interface LoginMapper {
	
	public Usuario iniciarSesion(Map<String,String> usuario);
	
	public List<OpcionMenu> cargarOpcionesMenu(String usuario);

}
