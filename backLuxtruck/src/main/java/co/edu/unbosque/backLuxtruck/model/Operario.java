package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operario")
public class Operario extends Trabajador {
	@OneToOne
	@JoinColumn(name = "vendedor_idPersona", nullable = false)
	private Trabajador trabajador;
	@Column(name = "desempenio", nullable = false)
	private int desempenio;

	public Operario() {
		// TODO Auto-generated constructor stub
	}

	
	public Operario(Trabajador trabajador, int desempenio) {
		super();
		this.trabajador = trabajador;
		this.desempenio = desempenio;
	}


	
	public Operario(Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}


	public Operario(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}


	public Trabajador getTrabajador() {
		return trabajador;
	}


	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}


	public int getDesempenio() {
		return desempenio;
	}

	public void setDesempenio(int desempenio) {
		this.desempenio = desempenio;
	}


	@Override
	public String toString() {
		return "Operario [trabajador=" + trabajador + ", desempenio=" + desempenio + "]";
	}

	
}
