package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDEDOR")
public class Vendedor extends Trabajador {

	@Column(name = "comision", columnDefinition = "DECIMAL(10,2)")
	private Double comision;

	public Vendedor() {

	}

	public Vendedor(double comision) {
		super();
		this.comision = comision;
	}

	public Vendedor(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia, double comision,
			EstadoTrabajador estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo, fechaIngreso, salario, estado, contrasenia, estadoTrabajador);
		this.comision = comision;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

}
