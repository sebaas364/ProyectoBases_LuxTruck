package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class OperarioDTO extends TrabajadorDTO{
	private int desempenio;

	public OperarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public OperarioDTO(int desempenio) {
		super();
		this.desempenio = desempenio;
	}

	public OperarioDTO(Date fechaIngreso, Double salario, String contrasenia, EstadoTrabajadorDTO estadoTrabajador) {
		super(fechaIngreso, salario, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}

	public OperarioDTO(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String contrasenia,
			EstadoTrabajadorDTO estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}

	public int getDesempenio() {
		return desempenio;
	}

	public void setDesempenio(int desempenio) {
		this.desempenio = desempenio;
	}
}
