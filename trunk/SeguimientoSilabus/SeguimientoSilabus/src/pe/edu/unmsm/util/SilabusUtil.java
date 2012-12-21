package pe.edu.unmsm.util;

import java.util.Calendar;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class SilabusUtil {

	public static String PATH=ResourceBundle.getBundle("pe.edu.unmsm.recursos.conf").getString("servidorArchivos");
	

	public static void flashScope(String clave, Object objeto) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.put(clave, objeto);
	}

	public static void escribir(String texto) {
		System.out.println(texto);
	}

	public static Object subirASesion(String clave, Object objeto)
			throws Exception {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put(clave, objeto);

	}

	public static Object obtenerDeSesion(String clave) throws Exception {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(clave);
	}

	public static String fechaActual() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.MONTH) + "/"
				+ calendar.get(Calendar.YEAR);
	}

	public static int getAnhoActual() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static void mensajeJSF(int severidad, String titulo, String detalle) {

		switch (severidad) {
			case 1:
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, titulo,
								detalle));break;
			case 2:
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,
								detalle));break;
			case 3:
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo,
								detalle));break;
			default:break;
		}

	}

}
