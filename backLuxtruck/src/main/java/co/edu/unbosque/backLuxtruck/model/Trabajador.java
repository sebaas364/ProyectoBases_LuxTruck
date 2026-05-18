package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRABAJADOR")
@Inheritance(strategy = InheritanceType.JOINED)
public class Trabajador extends Persona {

	@Column(name = "fechaIngreso", nullable = false)
	private Date fechaIngreso;

	@Column(name = "salario", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private Double salario;

	@Column(name = "contrasenia", nullable = false)
	private String contrasenia;

	@ManyToOne
	@JoinColumn(name = "idEstado", nullable = false)
	private EstadoTrabajador estadoTrabajador;

	public Trabajador() {

	}

	public Trabajador(Date fechaIngreso, Double salario, String contrasenia, EstadoTrabajador estadoTrabajador) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.contrasenia = contrasenia;
		this.estadoTrabajador = estadoTrabajador;
	}

	public Trabajador(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia, EstadoTrabajador estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo);
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.contrasenia = contrasenia;
		this.estadoTrabajador = estadoTrabajador;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public EstadoTrabajador getEstadoTrabajador() {
		return estadoTrabajador;
	}

	public void setEstadoTrabajador(EstadoTrabajador estadoTrabajador) {
		this.estadoTrabajador = estadoTrabajador;
	}

	
}
