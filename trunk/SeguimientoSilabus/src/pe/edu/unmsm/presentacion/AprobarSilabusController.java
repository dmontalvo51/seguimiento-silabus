package pe.edu.unmsm.presentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.DetalleSilabus;
import pe.edu.unmsm.negocio.modelo.Respuesta;
import pe.edu.unmsm.negocio.modelo.Silabus;
import pe.edu.unmsm.negocio.servicio.AplicacionService;
import pe.edu.unmsm.util.SilabusUtil;

@ViewScoped
@ManagedBean(name = "aprobarSilabus")
public class AprobarSilabusController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Curso curso;
	private Silabus silabus;
	private List<DetalleSilabus> listaDetalleSilabus;
	private DetalleSilabus detalle;

	private String pathGeneral = "";

	private StreamedContent archivoSilabus;

	@ManagedProperty("#{aplicacion}")
	AplicacionService aplicacionService;

	public AprobarSilabusController() {
		super();
		silabus = new Silabus();
		detalle = new DetalleSilabus();
		listaDetalleSilabus = new ArrayList<DetalleSilabus>();
	
	}

	@PostConstruct
	public void cargarDatos() {
		// Cargando datos del curso seleccionado
		try {
			setCurso((Curso) SilabusUtil.obtenerDeSesion("curso"));
		} catch (Exception e) {
			setCurso(new Curso());
			SilabusUtil
					.mensajeJSF(3, "Detalle silabus",
							"No se ha seleccionado un curso para el ver el detalle del silabus");
			e.printStackTrace();
		}

		// Ya tiene silabus, cargar la lista de detalle de silabus y los datos
		// del silabus
		silabus = aplicacionService.cargarSilabus(curso);
		listaDetalleSilabus = aplicacionService.cargarDetalleSilabus(silabus
				.getCodigoSilabus());
		
		//Cargando el archivo
	//	InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(SilabusUtil.PATH+silabus.getRuta());  
		InputStream stream;
		try {
			stream = new FileInputStream(new File(SilabusUtil.PATH+silabus.getRuta()));
			archivoSilabus = new DefaultStreamedContent(stream, "document/pdf", silabus.getCodigoSilabus()+"-"+curso.getDescripcion()+".pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String aprobarSilabus() {
		Respuesta r;
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
		.setKeepMessages(true);
		
		r=aplicacionService.aprobarSilabus(silabus.getCodigoSilabus());
		if(r.getEstado()==Respuesta.OK){
			SilabusUtil.mensajeJSF(0,"Silabus","El silabus fue aprobado");
		}else{
			
		}
		return "EvaluarSilabus.xhtml?faces-redirect=true";

	}

	public String rechazarSilabus() {
		Respuesta r;
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
		.setKeepMessages(true);
		
		r=aplicacionService.rechazarSilabus(silabus.getCodigoSilabus());
		if(r.getEstado()==Respuesta.OK){
			SilabusUtil.mensajeJSF(0,"Silabus","El silabus fue rechazado");
		}else{
			
		}
		return "EvaluarSilabus.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		return "EvaluarSilabus.xhtml?faces-redirect=true";

	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Silabus getSilabus() {
		return silabus;
	}

	public void setSilabus(Silabus silabus) {
		this.silabus = silabus;
	}

	public List<DetalleSilabus> getListaDetalleSilabus() {
		return listaDetalleSilabus;
	}

	public void setListaDetalleSilabus(List<DetalleSilabus> listaDetalleSilabus) {
		this.listaDetalleSilabus = listaDetalleSilabus;
	}

	public DetalleSilabus getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleSilabus detalle) {
		this.detalle = detalle;
	}

	public String getPathGeneral() {
		return pathGeneral;
	}

	public void setPathGeneral(String pathGeneral) {
		this.pathGeneral = pathGeneral;
	}

	public AplicacionService getAplicacionService() {
		return aplicacionService;
	}

	public void setAplicacionService(AplicacionService aplicacionService) {
		this.aplicacionService = aplicacionService;
	}

	public StreamedContent getArchivoSilabus() {
		return archivoSilabus;
	}

	public void setArchivoSilabus(StreamedContent archivoSilabus) {
		this.archivoSilabus = archivoSilabus;
	}

}
