package pe.edu.unmsm.presentacion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import pe.edu.unmsm.negocio.modelo.OpcionMenu;
import pe.edu.unmsm.negocio.modelo.Usuario;
import pe.edu.unmsm.util.SilabusUtil;

@SessionScoped
@ManagedBean(name="menu")
public class MenuController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1961809545155152958L;
	
	private Usuario usuario=new Usuario();
	private MenuModel modeloMenu=new DefaultMenuModel();
	
	
	public MenuController(){
	
		try {
			usuario=(Usuario)SilabusUtil.obtenerDeSesion("usuario");
			construirModelo(usuario.getOpcionesMenu());
		} catch (Exception e) {
			SilabusUtil.escribir("Error al obtener usuario de sesion");
			e.printStackTrace();
		}
			
		

	}
	
	public void construirModelo(List<OpcionMenu> listaOpcionesMenu){
		
		for(OpcionMenu om : listaOpcionesMenu){
			
			MenuItem menuItem = new MenuItem();
	        menuItem.setValue(om.getDescripcion());
	        menuItem.setUrl(om.getUrl());
	        menuItem.setIcon("/resources/img/"+om.getIcono());
	        modeloMenu.addMenuItem(menuItem);
	       
		}
		
	}
	


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public MenuModel getModeloMenu() {
		return modeloMenu;
	}

	public void setModeloMenu(MenuModel modeloMenu) {
		this.modeloMenu = modeloMenu;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	

}
