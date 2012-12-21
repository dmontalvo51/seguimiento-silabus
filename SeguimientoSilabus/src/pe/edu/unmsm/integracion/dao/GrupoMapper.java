package pe.edu.unmsm.integracion.dao;

import java.util.List;

import pe.edu.unmsm.negocio.modelo.Grupo;

public interface GrupoMapper {
	
	public List<Grupo> cargarGruposPorAvanzar(Grupo grupo) throws Exception;
	

}
