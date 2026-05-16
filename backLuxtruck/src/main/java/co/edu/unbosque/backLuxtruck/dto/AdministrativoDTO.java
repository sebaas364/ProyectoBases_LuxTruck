package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class AdministrativoDTO extends TrabajadorDTO{

	public AdministrativoDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdministrativoDTO(Date fechaIngreso, Double salario, String contrasenia,
			EstadoTrabajadorDTO estadoTrabajadordto) {
		super(fechaIngreso, salario, contrasenia, estadoTrabajadordto);
		// TODO Auto-generated constructor stub
	}

	public AdministrativoDTO(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Date fechaIngreso, Double salario, String estado, String contrasenia,
			EstadoTrabajadorDTO estadoTrabajador) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,
				correo, fechaIngreso, salario, estado, contrasenia, estadoTrabajador);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdministrativoDTO []";
	}


}
