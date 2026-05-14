package co.edu.unbosque.backLuxtruck.dto;

import java.time.LocalDate;

public class AdministrativoDTO extends TrabajadorDTO {
	public TrabajadorDTO trabajadordto;

	public AdministrativoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministrativoDTO(LocalDate fechaIngreso, int salario, String estado, String contrasenia) {
		super(fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}

	public AdministrativoDTO(Integer idPersona, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, int numeroDocumento, int telefono, String correo, String tipoDocumento) {
		super(idPersona, primerNombre, segundoNombre, primerApellido, segundoApellido, numeroDocumento, telefono,
				correo, tipoDocumento);
		// TODO Auto-generated constructor stub
	}

	public AdministrativoDTO(TrabajadorDTO trabajadordto) {
		super();
		this.trabajadordto = trabajadordto;
	}

	public TrabajadorDTO getTrabajadordto() {
		return trabajadordto;
	}

	public void setTrabajadordto(TrabajadorDTO trabajadordto) {
		this.trabajadordto = trabajadordto;
	}

	@Override
	public String toString() {
		return "AdministrativoDTO [trabajadordto=" + trabajadordto + "]";
	}

}
