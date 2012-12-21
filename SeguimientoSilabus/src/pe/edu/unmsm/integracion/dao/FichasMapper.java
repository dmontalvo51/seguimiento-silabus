package pe.edu.unmsm.integracion.dao;

import java.util.List;

import pe.edu.unmsm.negocio.modelo.Avance;
import pe.edu.unmsm.negocio.modelo.Ficha;
import pe.edu.unmsm.negocio.modelo.Grupo;

public interface FichasMapper {
	
	public void insertarFicha(Ficha ficha) throws Exception;
	
	public void insertarDetalleFicha(Avance avance) throws Exception;
	
}
