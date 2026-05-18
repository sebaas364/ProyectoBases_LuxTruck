package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class TrabajadorDTO extends PersonaDTO {

	private Date fechaIngreso;
	private Double salario;
	private String contrasenia;
	private EstadoTrabajadorDTO estadoTrabajadordto;
	private String rol;

	public TrabajadorDTO() {

	}

	public TrabajadorDTO(Date fechaIngreso, Double salario, String contrasenia, EstadoTrabajadorDTO estadoTrabajadordto) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.contrasenia = contrasenia;
		this.estadoTrabajadordto = estadoTrabajadordto;
	}

	public TrabajadorDTO(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String contrasenia, EstadoTrabajadorDTO estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo);
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.contrasenia = contrasenia;
		this.estadoTrabajadordto = estadoTrabajador;
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

	public EstadoTrabajadorDTO getEstadoTrabajadordto() {
		return estadoTrabajadordto;
	}

	public void setEstadoTrabajadordto(EstadoTrabajadorDTO estadoTrabajadordto) {
		this.estadoTrabajadordto = estadoTrabajadordto;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
