package pe.edu.unmsm.negocio.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.integracion.dao.LoginMapper;
import pe.edu.unmsm.negocio.modelo.OpcionMenu;
import pe.edu.unmsm.negocio.modelo.Usuario;

@Service(value = "seguridadService")
public class SeguridadService {
	
	@Autowired
	LoginMapper loginMapper;

	public SeguridadService() {

	}	
	
	public Usuario iniciarSesion(Map usuario) {

		Usuario usu=null;
		//usu= loginMapper.iniciarSesion(usuario);
		if(usuario.get("cuenta").equals(usuario.get("pass")))
			usu=new Usuario();
		
		usu.setCuenta("07200024");
			
		//Cargando las opciones de menu
		if (usu != null) {
			//usu.setOpcionesMenu(loginMapper.cargarOpcionesMenu(usu.getCuenta()));
			List<OpcionMenu> lo=new ArrayList<OpcionMenu>();
			lo.add(new OpcionMenu("/pages/Silabus.jsf","Silabus","silabus.jpg"));
			lo.add(new OpcionMenu("/pages/EvaluarSilabus.jsf","Evaluar silabus","revisarSilabus.jpg"));
			lo.add(new OpcionMenu("/pages/IngresarAvance.jsf","Ingresar avance","avances.jpg"));
			lo.add(new OpcionMenu("/pages/RevisarAvances.jsf","Revisar avances	","revisarAvances.jpg"));
			usu.setOpcionesMenu(lo);
			
			return usu;
		} else
			return null;
	}
	
	public LoginMapper getLoginMapper() {
		return loginMapper;
	}

	public void setLoginMapper(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}
}
