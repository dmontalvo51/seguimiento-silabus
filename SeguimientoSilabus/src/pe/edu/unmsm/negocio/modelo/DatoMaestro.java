package pe.edu.unmsm.negocio.modelo;

public class DatoMaestro {
	
	private int codigo;
	private String descripcion;
	
	public DatoMaestro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DatoMaestro(int codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}


	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
