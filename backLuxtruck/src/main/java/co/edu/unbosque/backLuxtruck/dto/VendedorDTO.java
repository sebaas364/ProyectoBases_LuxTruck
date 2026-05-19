package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class VendedorDTO extends TrabajadorDTO {

	private Double comision;

	public VendedorDTO() {

	}

	public VendedorDTO(Date fechaIngreso, Double salario, String contrasenia, EstadoTrabajadorDTO estadoTrabajador) {
		super(fechaIngreso, salario, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}


	public VendedorDTO(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String contrasenia,
			EstadoTrabajadorDTO estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}


	public VendedorDTO(double comision) {
		super();
		this.comision = comision;
	}

	
	public Double getComision() {
	    return comision;
	}

	public void setComision(Double comision) {
	    this.comision = comision;
	}

	@Override
	public String toString() {
		return "VendedorDTO [comision=" + comision + "]";
	}

}
