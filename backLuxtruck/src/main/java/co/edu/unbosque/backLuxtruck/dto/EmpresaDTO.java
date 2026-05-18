package co.edu.unbosque.backLuxtruck.dto;

public class EmpresaDTO {

	private Integer idEmpresa;
	private String NIT;
	private String nombre;
	private String telefono;
	private String correo; 
	
	public EmpresaDTO() {
		// TODO Auto-generated constructor stub
	}

	public EmpresaDTO(Integer idEmpresa, String nIT, String nombre, String telefono, String correo) {
		super();
		this.idEmpresa = idEmpresa;
		this.NIT = nIT;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		NIT = nIT;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
