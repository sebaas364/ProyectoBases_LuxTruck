package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="VENDEDOR")
public class Vendedor extends Trabajador{

	@Column(name="comision")
	private int comision;
	@Column(name="zonaVenta", nullable = false)
	private String zonaVenta;
	
	public Vendedor() {
		
	}

	public Vendedor(int comision, String zonaVenta) {
		super();
		this.comision = comision;
		this.zonaVenta = zonaVenta;
	}

	public Vendedor(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia, int comision, String zonaVenta) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo, fechaIngreso, salario, estado, contrasenia);
		this.comision = comision;
		this.zonaVenta = zonaVenta;
	}

	public int getComision() {
		return comision;
	}

	public void setComision(int comision) {
		this.comision = comision;
	}

	public String getZonaVenta() {
		return zonaVenta;
	}

	public void setZonaVenta(String zonaVenta) {
		this.zonaVenta = zonaVenta;
	}
	
	
}
