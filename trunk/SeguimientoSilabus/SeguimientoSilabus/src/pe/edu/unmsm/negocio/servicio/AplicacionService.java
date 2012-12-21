package pe.edu.unmsm.negocio.servicio;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.integracion.dao.CursosMapper;
import pe.edu.unmsm.integracion.dao.FichasMapper;
import pe.edu.unmsm.integracion.dao.GrupoMapper;
import pe.edu.unmsm.integracion.dao.SilabusMapper;
import pe.edu.unmsm.negocio.modelo.Avance;
import pe.edu.unmsm.negocio.modelo.Curso;
import pe.edu.unmsm.negocio.modelo.DetalleSilabus;
import pe.edu.unmsm.negocio.modelo.Ficha;
import pe.edu.unmsm.negocio.modelo.Grupo;
import pe.edu.unmsm.negocio.modelo.Respuesta;
import pe.edu.unmsm.negocio.modelo.Silabus;
import pe.edu.unmsm.util.SilabusUtil;

@Service(value = "aplicacion")
public class AplicacionService {

	@Autowired
	private SilabusMapper silabusMapper;

	@Autowired
	private CursosMapper cursosMapper;
	
	@Autowired
	private GrupoMapper grupoMapper;
	
	@Autowired
	private FichasMapper fichasMapper;
	
	

	public List<Curso> cargarListaCursos() {
		try {
			return cursosMapper.cargarCursos();
		} catch (Exception e) {
			e.printStackTrace();
			SilabusUtil.escribir("Error al cargar la lista de cursos");
			return new ArrayList<Curso>();
		}
	}

	public List<Curso> cargarListaCursosConSilabus() {
		try {
			return cursosMapper.cargarCursosConSilabus();
		} catch (Exception e) {
			e.printStackTrace();
			SilabusUtil.escribir("Error al cargar la lista de cursos");
			return new ArrayList<Curso>();
		}
	}

	public Silabus cargarSilabus(Curso curso) {
		try {
			return silabusMapper.cargarSilabus(curso);
		} catch (Exception e) {
			e.printStackTrace();
			SilabusUtil.escribir("Error al cargar el silabus del curso");
			return new Silabus();
		}
	}

	public List<Grupo> cargarGruposPorAvanzar(Grupo grupo) {
		try {
			return grupoMapper.cargarGruposPorAvanzar(grupo);
		} catch (Exception e) {
			e.printStackTrace();
			SilabusUtil.escribir("Error al cargar la lista de grupos.");
			return new ArrayList<Grupo>();
		}
	}
	
	public List<DetalleSilabus> cargarDetalleSilabus(String codigoSilabus) {
		try {
			return silabusMapper.listaDetalleSilabus(codigoSilabus);
		} catch (Exception e) {
			e.printStackTrace();
			SilabusUtil
					.escribir("Error al cargar la lista de detalle del silabus "
							+ codigoSilabus);
			return new ArrayList<DetalleSilabus>();
		}
	}
	
	

	public Respuesta ingresarSilabus(Silabus silabus,
			List<DetalleSilabus> listaDetalle) {
		Respuesta r = new Respuesta();
		try {
			silabusMapper.insertarSilabus(silabus);

			for (DetalleSilabus d : listaDetalle) {
				d.setCodigoSilabus(silabus.getCodigoSilabus());
				silabusMapper.insertarDetalleSilabus(d);
			}
			r.setEstado(Respuesta.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setEstado(Respuesta.ERROR);
		}
		return r;
	}

	public Respuesta aprobarSilabus(String codigoSilabus) {
		Respuesta r = new Respuesta();

		try {
			silabusMapper.aprobarSilabus(codigoSilabus);
			r.setEstado(Respuesta.OK);
		} catch (Exception e) {
			e.printStackTrace();
			r.setEstado(Respuesta.ERROR);
		}

		return r;
	}

	public Respuesta rechazarSilabus(String codigoSilabus) {
		Respuesta r = new Respuesta();

		try {
			silabusMapper.rechazarSilabus(codigoSilabus);
			r.setEstado(Respuesta.OK);
		} catch (Exception e) {
			r.setEstado(Respuesta.ERROR);
		}

		return r;
	}

	public Respuesta actualizarSilabus(Silabus silabus,List<DetalleSilabus> listaDetalleSilabus) {
		Respuesta r = new Respuesta();
		try {
			silabusMapper.borrarDetalleSilabus(silabus.getCodigoSilabus());
			silabusMapper.actualizarSilabus(silabus);

			for (DetalleSilabus d : listaDetalleSilabus) {
				d.setCodigoSilabus(silabus.getCodigoSilabus());
				silabusMapper.insertarDetalleSilabus(d);
			}

			r.setEstado(Respuesta.OK);
		} catch (Exception e) {
			r.setEstado(Respuesta.ERROR);
			e.printStackTrace();
		}
		return r;
	}
	
	
	public Curso cargarCurso(Curso curso) {
		
		try {
			return cursosMapper.cargarCursoPorCodigo(curso);
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}
		
	}
	
	
	public Respuesta ingresarFichaAvance(Ficha ficha,List<Avance> listaAvance){
		Respuesta r=new Respuesta();
		
		try{
			ficha.setCodigoFicha(ficha.getCodigoCurso()+"-"+ficha.getCodigoGrupo()+"-"+Math.floor(Math.random()*100));
			
			fichasMapper.insertarFicha(ficha);
			
			for(Avance a:listaAvance){
				a.setCodigoFichaAvance(ficha.getCodigoFicha());
				fichasMapper.insertarDetalleFicha(a);
			}
			r.setEstado(Respuesta.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			r.setEstado(Respuesta.ERROR);
		}
				
		return r;
	}
	
	

	public SilabusMapper getSilabusMapper() {
		return silabusMapper;
	}

	public void setSilabusMapper(SilabusMapper silabusMapper) {
		this.silabusMapper = silabusMapper;
	}

	public CursosMapper getCursosMapper() {
		return cursosMapper;
	}

	public void setCursosMapper(CursosMapper cursosMapper) {
		this.cursosMapper = cursosMapper;
	}

	public GrupoMapper getGrupoMapper() {
		return grupoMapper;
	}

	public void setGrupoMapper(GrupoMapper grupoMapper) {
		this.grupoMapper = grupoMapper;
	}

}
