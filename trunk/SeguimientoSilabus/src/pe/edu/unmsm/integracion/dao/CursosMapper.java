package pe.edu.unmsm.integracion.dao;

import java.util.List;

import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.Grupo;

public interface CursosMapper {
	
	public List<Curso> cargarCursos() throws Exception;

	public List<Curso> cargarCursosConSilabus() throws Exception;
	
	public Curso cargarCursoPorCodigo(Curso curso) throws Exception;
}
