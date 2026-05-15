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
	
	@Column(name = "desempenio")
	private int desempenio;

	public Operario() {
		// TODO Auto-generated constructor stub
	}

	
	public Operario(int desempenio) {
		super();
		this.desempenio = desempenio;
	}


	public Operario(Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(fechaIngreso, salario, contrasenia);
		// TODO Auto-generated constructor stub
	}


	public Operario(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}

	public int getDesempenio() {
		return desempenio;
	}

	public void setDesempenio(int desempenio) {
		this.desempenio = desempenio;
	}
	
}
