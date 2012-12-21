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
import pe.edu.unmsm.negocio.modelo.DetalleSilabus;
import pe.edu.unmsm.negocio.modelo.Respuesta;
import pe.edu.unmsm.negocio.modelo.Silabus;
import pe.edu.unmsm.negocio.servicio.AplicacionService;
import pe.edu.unmsm.util.SilabusUtil;

@ViewScoped
@ManagedBean(name = "evaluarSilabus")
public class EvaluarSilabusController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Curso curso;
	private Silabus silabus;
	private List<DetalleSilabus> listaDetalleSilabus;
	private DetalleSilabus detalle;

	private String pathGeneral = "D://Silabus/";

	private int[] listaSemanas = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16 };

	private String modo; // ACTUALIZAR, INSERTAR

	@ManagedProperty("#{aplicacion}")
	AplicacionService aplicacionService;

	public EvaluarSilabusController() {
		super();
		//silabus = new Silabus();
		detalle = new DetalleSilabus();
		listaDetalleSilabus = new ArrayList<DetalleSilabus>();
		// TODO Auto-generated constructor stub
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
		setModo("ACTUALIZAR");

	}

	public void agregarDetalleSilabus() {

		listaDetalleSilabus.add(detalle);
		detalle = new DetalleSilabus();
	}

	public String guardarSilabus() {

		Respuesta r = new Respuesta();
		silabus.setCodigoCurso(curso.getCodigo());
		silabus.setPlan(curso.getPlan());
		silabus.setRuta(getPathGeneral() + silabus.getCodigoSilabus() + ".pdf");

		if (getModo().equals("ACTUALIZAR"))
			r = aplicacionService.ingresarSilabus(silabus, listaDetalleSilabus);
		else
			r = aplicacionService.ingresarSilabus(silabus, listaDetalleSilabus);

		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);

		if (r.getEstado() == Respuesta.OK) {
			SilabusUtil.mensajeJSF(0, "Silabus",
					"El silabus fue ingresado correctamente");
			return "Inicio.xhtml?faces-redirect=true";
		} else {
			SilabusUtil.mensajeJSF(0, "Silabus",
					"Hubo un error al ingresar el silabus");
			return null;
		}

	}

	public String cancelar() {
		return "Silabus.xhtml?faces-redirect=true";
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

	public int[] getListaSemanas() {
		return listaSemanas;
	}

	public void setListaSemanas(int[] listaSemanas) {
		this.listaSemanas = listaSemanas;
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

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

}
