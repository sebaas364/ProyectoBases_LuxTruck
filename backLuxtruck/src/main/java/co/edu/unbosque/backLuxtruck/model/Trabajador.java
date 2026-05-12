package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRABAJADOR")
public class Trabajador extends Persona {

	@Column(name = "fechaIngreso", nullable = false)
	private Date fechaIngreso;

	@Column(name = "salario", nullable = false)
	private Double salario;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "contrasenia", nullable = false)
	private String contrasenia;

	public Trabajador() {

	}

	public Trabajador(Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.estado = estado;
		this.contrasenia = contrasenia;
	}

	public Trabajador(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo);
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.estado = estado;
		this.contrasenia = contrasenia;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
