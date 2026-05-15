package co.edu.unbosque.backLuxtruck.dto;

import java.time.LocalDate;

public class VendedorDTO extends TrabajadorDTO {

	public double comision;
	private String zonaVenta;

	public VendedorDTO(double comision, String zonaVenta) {
		super();
		this.comision = comision;
		this.zonaVenta = zonaVenta;
	}

	public VendedorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendedorDTO(LocalDate fechaIngreso, double salario, String estado, String contrasenia) {
		super(fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}



	public VendedorDTO(Integer idPersona, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, int numeroDocumento, int telefono, String correo, String tipoDocumento) {
		super(idPersona, primerNombre, segundoNombre, primerApellido, segundoApellido, numeroDocumento, telefono, correo,
				tipoDocumento);
		// TODO Auto-generated constructor stub
	}

	public double getComision() {
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

	@Override
	public String toString() {
		return "VendedorDTO [comision=" + comision + ", zonaVenta=" + zonaVenta + "]";
	}

}
