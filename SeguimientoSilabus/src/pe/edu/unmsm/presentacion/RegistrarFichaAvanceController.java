package pe.edu.unmsm.presentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import pe.edu.unmsm.negocio.modelo.Avance;
import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.DetalleSilabus;
import pe.edu.unmsm.negocio.modelo.Ficha;
import pe.edu.unmsm.negocio.modelo.Grupo;
import pe.edu.unmsm.negocio.modelo.Respuesta;
import pe.edu.unmsm.negocio.modelo.Silabus;
import pe.edu.unmsm.negocio.servicio.AplicacionService;
import pe.edu.unmsm.util.SilabusUtil;

@ViewScoped
@ManagedBean(name = "registrarFicha")
public class RegistrarFichaAvanceController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Curso curso;
	private Silabus silabus;
	private List<DetalleSilabus> listaDetalleSilabus;
	private Grupo grupo;
	private int[] listaSemanas = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
	private List<DetalleSilabus> listaActividades; 

	private String modo; // ACTUALIZAR, INSERTAR
	private String rutaArchivoSilabus;
	
	
	private Avance avance;
	private Date fecha;
	private List<Avance> listaAvances;
	private Avance avanceSeleccionado;
	

	@ManagedProperty("#{aplicacion}")
	AplicacionService aplicacionService;

	public RegistrarFichaAvanceController() {
		super();
		File f = new File("");
		avance=new Avance();
		avanceSeleccionado=new Avance();
		silabus = new Silabus();
		listaDetalleSilabus = new ArrayList<DetalleSilabus>();
		listaActividades=new ArrayList<DetalleSilabus>();
		listaAvances=new ArrayList<Avance>();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void cargarDatos() {
		// Cargando datos del curso seleccionado
		try {
			setGrupo((Grupo) SilabusUtil.obtenerDeSesion("grupo"));
		} catch (Exception e) {
			setCurso(new Curso());
			SilabusUtil
					.mensajeJSF(3, "Detalle silabus",
							"No se ha seleccionado un curso para el ver el detalle del silabus");
			e.printStackTrace();
		}
		
		Curso c=new Curso();
		c.setCodigo(grupo.getCodigoCurso());
		c.setPlan(grupo.getPlan());
		curso=aplicacionService.cargarCurso(c);		
		silabus = aplicacionService.cargarSilabus(curso);
		listaDetalleSilabus = aplicacionService.cargarDetalleSilabus(silabus
				.getCodigoSilabus());
		
		setRutaArchivoSilabus(SilabusUtil.PATH + silabus.getRuta());
		SilabusUtil.escribir("Este es el path "+SilabusUtil.PATH);

	}

	public String guardarSilabus() {

		Respuesta r = new Respuesta();

		SilabusUtil.escribir("Modo " + getModo());

		if (getModo().equals("ACTUALIZAR"))
			r = aplicacionService.actualizarSilabus(silabus,
					listaDetalleSilabus);
		else
			r = aplicacionService.ingresarSilabus(silabus, listaDetalleSilabus);

		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);

		if (r.getEstado() == Respuesta.OK) {
			if (null != null) {
				SilabusUtil.escribir("Se guardara el archivo");
				guardarArchivo(silabus.getRuta());
			} else
				SilabusUtil.escribir("NO se guardara el archivo");

			if (getModo().equals("ACTUALIZAR"))
				SilabusUtil.mensajeJSF(1, "Silabus",
						"El silabus fue modificado correctamente");
			else if (getModo().equals("INGRESAR"))
				SilabusUtil.mensajeJSF(1, "Silabus",
						"El silabus fue ingresado correctamente");

			return "Silabus.xhtml?faces-redirect=true";
		} else {
			SilabusUtil.mensajeJSF(3, "Silabus",
					"Hubo un error al ingresar/modificar el silabus");
			return null;
		}

	}
	
	public void agregarAvance(){
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		avance.setFechaRegistrada(formateador.format(fecha));
		avance.setCodigoSilabus(silabus.getCodigoSilabus());
		listaAvances.add(avance);
		avance=new Avance();
	}
	
	public void eliminarAvance(){
		SilabusUtil.escribir("Ingreso al eliminar"+avanceSeleccionado.getDetalleActividad());
		for(Avance a:listaAvances){
			if(a.equals(avanceSeleccionado))
				listaAvances.remove(a);
		}
	}
	

	public void subirArchivo(FileUploadEvent e) {
		SilabusUtil.escribir("Iniciando subida de archivo");
		if (e.getFile() != null) {
			// setArchivoSilabus(e.getFile());
			SilabusUtil.escribir("Se guardó el archivo en memoria");
		} else
			SilabusUtil.escribir("Archivo no válido");
	}

	public void guardarArchivo(String ruta) {
		try {
			InputStream inputStream = null; // archivoSilabus.getInputstream();
			OutputStream out = new FileOutputStream(new File(SilabusUtil.PATH
					+ ruta));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String guardarFichaAvance(){
		
		Respuesta r=new Respuesta();
		
		SilabusUtil.escribir("Guardando la ficha");
		
		Ficha ficha=new Ficha();
		ficha.setCodigoEstadoFicha(2);
		ficha.setCodigoCurso(grupo.getCodigoCurso());
		ficha.setPlanCurso(grupo.getPlan());
		ficha.setCodigoGrupo(grupo.getCodigoGrupo());
		ficha.setAnoGrupo(grupo.getAnoGrupo());
		ficha.setCicloGrupo(grupo.getCicloGrupo());
		ficha.setCodigoDocente(grupo.getCodigoDocente());
		
		r=aplicacionService.ingresarFichaAvance(ficha,listaAvances);
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
		.setKeepMessages(true);
		
		
		if(r.getEstado()==(Respuesta.OK)){
			SilabusUtil.mensajeJSF(1,"Ficha","Se guardo correctamente la ficha de avance");
			return "IngresarAvance.xhtml?faces-redirect=true";
		}else{
			SilabusUtil.mensajeJSF(3,"Ficha","Ocurrió un error al ingresar la ficha de avance");
			listaAvances=new ArrayList<Avance>();
			avance=new Avance();
			return null;
		}
	}
	
	public void seleccionaSemana(){
		SilabusUtil.escribir("Selecciona semana");
		listaActividades=new ArrayList<DetalleSilabus>();
		for(DetalleSilabus d:listaDetalleSilabus){
			if(d.getNroSemana()==avance.getNroSemana())
				listaActividades.add(d);
		}
			
	}
	
	public void seleccionaActividad(){
		SilabusUtil.escribir("Selecciona actividad");
		for(DetalleSilabus d:listaActividades){
			if(d.getNroActividad()==avance.getNroActividad())
				avance.setDetalleActividad(d.getDetalle());
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

	public int[] getListaSemanas() {
		return listaSemanas;
	}

	public void setListaSemanas(int[] listaSemanas) {
		this.listaSemanas = listaSemanas;
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

	public String getRutaArchivoSilabus() {
		return rutaArchivoSilabus;
	}

	public void setRutaArchivoSilabus(String rutaArchivoSilabus) {
		this.rutaArchivoSilabus = rutaArchivoSilabus;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Avance getAvance() {
		return avance;
	}

	public void setAvance(Avance avance) {
		this.avance = avance;
	}

	public List<Avance> getListaAvances() {
		return listaAvances;
	}

	public void setListaAvances(List<Avance> listaAvances) {
		this.listaAvances = listaAvances;
	}

	public List<DetalleSilabus> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(List<DetalleSilabus> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public Avance getAvanceSeleccionado() {
		return avanceSeleccionado;
	}

	public void setAvanceSeleccionado(Avance avanceSeleccionado) {
		this.avanceSeleccionado = avanceSeleccionado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	

}
