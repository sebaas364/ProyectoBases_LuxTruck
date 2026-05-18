package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrativo")
public class Administrativo extends Trabajador{

	public Administrativo() {
		// TODO Auto-generated constructor stub
	}
	

	public Administrativo(Date fechaIngreso, Double salario, String estado, String contrasenia,EstadoTrabajador estadoTrabajador) {
		super(fechaIngreso, salario, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}


	public Administrativo( Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia, EstadoTrabajador estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, estado, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}


}
