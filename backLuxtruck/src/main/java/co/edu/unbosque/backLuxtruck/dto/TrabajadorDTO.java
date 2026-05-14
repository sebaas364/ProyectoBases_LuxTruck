package co.edu.unbosque.backLuxtruck.dto;

import java.time.LocalDate;

public class TrabajadorDTO extends PersonaDTO {

	public LocalDate fechaIngreso;
	public int salario;
	public String estado;
	public String contrasenia;

	public TrabajadorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrabajadorDTO(Integer idPersona, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, int numeroDocumento, int telefono, String correo, String tipoDocumento) {
		super(idPersona, primerNombre, segundoNombre, primerApellido, segundoApellido, numeroDocumento, telefono,
				correo, tipoDocumento);
		// TODO Auto-generated constructor stub
	}

	public TrabajadorDTO(LocalDate fechaIngreso, int salario, String estado, String contrasenia) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.estado = estado;
		this.contrasenia = contrasenia;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
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

	@Override
	public String toString() {
		return "TrabajadorDTO [fechaIngreso=" + fechaIngreso + ", salario=" + salario + ", estado=" + estado
				+ ", contrasenia=" + contrasenia + "]";
	}

}
