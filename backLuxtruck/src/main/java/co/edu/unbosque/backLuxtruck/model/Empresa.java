package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPRESA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpresa")
	private Integer idEmpresa;
	@Column(name="NIT", nullable = false, unique = true)
	private String NIT;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "telefono", nullable = false)
	private String telefono;
	@Column(name = "correo", nullable = false, unique = true)
	private String correo; 
	
	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	public Empresa(Integer idEmpresa, String nIT, String nombre, String telefono, String correo) {
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
