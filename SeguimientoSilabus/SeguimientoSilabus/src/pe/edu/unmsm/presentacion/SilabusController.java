package pe.edu.unmsm.presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.SelectEvent;

import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.Silabus;
import pe.edu.unmsm.negocio.servicio.AplicacionService;
import pe.edu.unmsm.util.SilabusUtil;

@ViewScoped
@ManagedBean(name="silabus")
public class SilabusController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Curso> listaCursos;
	private List<Integer> listaPlanes;
	private List<Silabus> listaSilabus;
	
	private Curso cursoSeleccionado;
	
	@ManagedProperty("#{aplicacion}")
	private AplicacionService aplicacionService;
	
	
	private boolean bloquearBoton=true;
	
	public SilabusController() {
		super();
	}
	
	@PostConstruct
	public void cargarDatos(){
		
		String origen = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServletPath();
		origen = origen.substring(7, origen.length() - 4);
		
		if(origen.equals("Silabus"))
			listaCursos=aplicacionService.cargarListaCursos();
		else if(origen.equals("EvaluarSilabus"))
			listaCursos=aplicacionService.cargarListaCursosConSilabus();
	
	}			
	
	public void seleccionaCurso(SelectEvent event){
		SilabusUtil.escribir("selecciono un curso: "+cursoSeleccionado.getDescripcion());
		setCursoSeleccionado((Curso)event.getObject());
		if(((Curso)event.getObject())!=null){
			bloquearBoton=false;
		}else{
			bloquearBoton=true;
		}
	}
	
	public String verSilabus(){
		
		String origen = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServletPath();
		origen = origen.substring(7, origen.length() - 4);
		
		try {
			SilabusUtil.subirASesion("curso",cursoSeleccionado);
		} catch (Exception e) {
			SilabusUtil.escribir("Error al subir a sesion curso seleccionado");
			e.printStackTrace();
		}
		
		if(origen.equals("Silabus"))
			return "DetalleSilabus.xhtml?faces-redirect=true";
		else if(origen.equals("EvaluarSilabus"))
			return "AprobarSilabus.xhtml?faces-redirect=true";
		else
			return null;
	}
	

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public AplicacionService getAplicacionService() {
		return aplicacionService;
	}

	public void setAplicacionService(AplicacionService aplicacionService) {
		this.aplicacionService = aplicacionService;
	}

	public List<Integer> getListaPlanes() {
		return listaPlanes;
	}

	public void setListaPlanes(List<Integer> listaPlanes) {
		this.listaPlanes = listaPlanes;
	}

	public List<Silabus> getListaSilabus() {
		return listaSilabus;
	}

	public void setListaSilabus(List<Silabus> listaSilabus) {
		this.listaSilabus = listaSilabus;
	}

	public Curso getCursoSeleccionado() {
		return cursoSeleccionado;
	}

	public void setCursoSeleccionado(Curso cursoSeleccionado) {
		this.cursoSeleccionado = cursoSeleccionado;
	}

	public boolean isBloquearBoton() {
		return bloquearBoton;
	}

	public void setBloquearBoton(boolean bloquearBoton) {
		this.bloquearBoton = bloquearBoton;
	}
	
	

}
