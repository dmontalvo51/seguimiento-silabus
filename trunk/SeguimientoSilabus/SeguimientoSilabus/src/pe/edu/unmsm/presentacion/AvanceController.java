package pe.edu.unmsm.presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.Docente;
import pe.edu.unmsm.negocio.modelo.Grupo;
import pe.edu.unmsm.negocio.modelo.Usuario;
import pe.edu.unmsm.negocio.servicio.AplicacionService;
import pe.edu.unmsm.util.SilabusUtil;


@ViewScoped
@ManagedBean(name = "avance")
public class AvanceController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{aplicacion}")
	AplicacionService aplicacionService;

	private Grupo grupoSeleccionado;
	private List<Grupo> listaGrupos;
	private Docente docente;
	
	private boolean bloquearBoton;
	
	
	public AvanceController() {
		super();
		listaGrupos=new ArrayList<Grupo>();
		grupoSeleccionado=new Grupo();
	}
	
	@PostConstruct	
	public void cargarDatos(){
		Grupo g=new Grupo();
		/*
		try {
			g.setCodigoDocente(((Usuario)SilabusUtil.obtenerDeSesion("usuario")).getCuenta());
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		listaGrupos=aplicacionService.cargarGruposPorAvanzar(g);
		
	}
	
	public String ingresarAvance(){
		
		String origen = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServletPath();
		origen = origen.substring(7, origen.length() - 4);
		
		try {
			SilabusUtil.subirASesion("grupo",grupoSeleccionado);
		} catch (Exception e) {
			SilabusUtil.escribir("Error al subir a sesion grupo seleccionado");
			e.printStackTrace();
		}
		
		return "RegistrarFichaAvance.xhtml?faces-redirect=true";

	}
	
	public void seleccionaGrupo(SelectEvent event){
		if(((Grupo)event.getObject())!=null){
			bloquearBoton=false;
		}else{
			bloquearBoton=true;
		}
	}
	
	
	
	
	public AplicacionService getAplicacionService() {
		return aplicacionService;
	}

	public void setAplicacionService(AplicacionService aplicacionService) {
		this.aplicacionService = aplicacionService;
	}



	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}


	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}


	public Docente getDocente() {
		return docente;
	}


	public void setDocente(Docente docente) {
		this.docente = docente;
	}


	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}


	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public boolean isBloquearBoton() {
		return bloquearBoton;
	}

	public void setBloquearBoton(boolean bloquearBoton) {
		this.bloquearBoton = bloquearBoton;
	}


	

}
