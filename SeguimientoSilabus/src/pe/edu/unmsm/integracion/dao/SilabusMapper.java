package pe.edu.unmsm.integracion.dao;

import java.util.List;

import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.DetalleSilabus;
import pe.edu.unmsm.negocio.modelo.Silabus;

public interface SilabusMapper {
	
	public Silabus cargarSilabus(Curso curso) throws Exception;
	
	public List<DetalleSilabus> listaDetalleSilabus(String codigoSilabus) throws Exception;
	
	public void insertarSilabus(Silabus silabus) throws Exception;
	
	public void actualizarSilabus(Silabus silabus) throws Exception;
	
	public void insertarDetalleSilabus(DetalleSilabus detalle) throws Exception;
	
	public void borrarDetalleSilabus(String codigoSilabus) throws Exception;
	
	public void aprobarSilabus(String codigoSilabus) throws Exception;
	
	public void rechazarSilabus(String codigoSilabus) throws Exception;
	
	
	
}
