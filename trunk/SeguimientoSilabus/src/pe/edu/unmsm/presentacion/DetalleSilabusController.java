package pe.edu.unmsm.presentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.DetalleSilabus;
import pe.edu.unmsm.negocio.modelo.Respuesta;
import pe.edu.unmsm.negocio.modelo.Silabus;
import pe.edu.unmsm.negocio.servicio.AplicacionService;
import pe.edu.unmsm.util.SilabusUtil;

@ViewScoped
@ManagedBean(name="detalleSilabus")
public class DetalleSilabusController  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Curso curso;
	private Silabus silabus;
	private List<DetalleSilabus> listaDetalleSilabus;
	private DetalleSilabus detalle;
	private UploadedFile archivoSilabus;  
	
	private int[] listaSemanas={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	
	private String modo; //ACTUALIZAR, INSERTAR
	
	private String rutaArchivoSilabus;
		
	
	@ManagedProperty("#{aplicacion}")
	AplicacionService aplicacionService;
	
	public DetalleSilabusController() {
		super();
		File f=new File("");
	
		silabus=new Silabus();
		detalle=new DetalleSilabus();
		listaDetalleSilabus=new ArrayList<DetalleSilabus>();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void cargarDatos(){
		//Cargando datos del curso seleccionado
		try {
			setCurso((Curso)SilabusUtil.obtenerDeSesion("curso"));
		} catch (Exception e) {
			setCurso(new Curso());
			SilabusUtil.mensajeJSF(3,"Detalle silabus", "No se ha seleccionado un curso para el ver el detalle del silabus");
			e.printStackTrace();
		}
		
		
		if(curso.getTieneSilabus()==1){
			//Ya tiene silabus, cargar la lista de detalle de silabus y los datos del silabus
			silabus=aplicacionService.cargarSilabus(curso);
			listaDetalleSilabus=aplicacionService.cargarDetalleSilabus(silabus.getCodigoSilabus());
			setRutaArchivoSilabus(SilabusUtil.PATH+silabus.getRuta());
			setModo("ACTUALIZAR");
		}else{
			//Si no tieene sialbus se genera su codigo automaticamente
			getSilabus().setCodigoSilabus("S"+getCurso().getCodigo());
			silabus.setCodigoCurso(curso.getCodigo());
			silabus.setPlan(curso.getPlan());
			silabus.setRuta("/silabus/"+silabus.getCodigoSilabus()+".pdf");
			setModo("INGRESAR");
		}
	}
	
	public void agregarDetalleSilabus(){
		
		listaDetalleSilabus.add(detalle);
		detalle=new DetalleSilabus();
	}
	
	
	public String guardarSilabus(){
		
		Respuesta r=new Respuesta();
		
		SilabusUtil.escribir("Modo "+getModo());
				
		if(getModo().equals("ACTUALIZAR"))
			r=aplicacionService.actualizarSilabus(silabus, listaDetalleSilabus);
		else 
			r=aplicacionService.ingresarSilabus(silabus, listaDetalleSilabus);
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
		.setKeepMessages(true);
		
		if(r.getEstado()==Respuesta.OK){
			if(archivoSilabus!=null){
				SilabusUtil.escribir("Se guardara el archivo");
				guardarArchivo(silabus.getRuta());
			}else
				SilabusUtil.escribir("NO se guardara el archivo");
			

			if(getModo().equals("ACTUALIZAR"))
				SilabusUtil.mensajeJSF(1,"Silabus","El silabus fue modificado correctamente");
			else if(getModo().equals("INGRESAR"))
				SilabusUtil.mensajeJSF(1,"Silabus","El silabus fue ingresado correctamente");

			return "Silabus.xhtml?faces-redirect=true";
		}else{
			SilabusUtil.mensajeJSF(3,"Silabus","Hubo un error al ingresar/modificar el silabus");
			return null;
		}

	}
	
	
	public void subirArchivo(FileUploadEvent e){
		SilabusUtil.escribir("Iniciando subida de archivo");
		if(e.getFile()!=null){
			setArchivoSilabus(e.getFile());
			SilabusUtil.escribir("Se guardó el archivo en memoria");
		}else
			SilabusUtil.escribir("Archivo no válido");
	}
	
	public void guardarArchivo(String ruta){
		 try {
	            InputStream inputStream = archivoSilabus.getInputstream();
	            OutputStream out = new FileOutputStream(new File(SilabusUtil.PATH+ruta));
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
	
	public String cancelar(){
		
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

	public UploadedFile getArchivoSilabus() {
		return archivoSilabus;
	}

	public void setArchivoSilabus(UploadedFile archivoSilabus) {
		this.archivoSilabus = archivoSilabus;
	}

	public String getRutaArchivoSilabus() {
		return rutaArchivoSilabus;
	}

	public void setRutaArchivoSilabus(String rutaArchivoSilabus) {
		this.rutaArchivoSilabus = rutaArchivoSilabus;
	}


}
