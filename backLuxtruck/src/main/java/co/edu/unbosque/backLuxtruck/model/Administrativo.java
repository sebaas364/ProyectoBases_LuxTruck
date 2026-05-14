package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrativo")
public class Administrativo extends Trabajador{
	@OneToOne
	@JoinColumn(name = "trabajador_idPersona", nullable = false)
	private Trabajador trabajador;

	public Administrativo() {
		// TODO Auto-generated constructor stub
	}
	

	public Administrativo(Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}


	public Administrativo(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}


	public Administrativo(Trabajador trabajador) {
		super();
		this.trabajador = trabajador;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	@Override
	public String toString() {
		return "Administrativo [trabajador=" + trabajador + "]";
	}

}
