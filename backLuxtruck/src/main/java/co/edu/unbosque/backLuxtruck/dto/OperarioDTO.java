package co.edu.unbosque.backLuxtruck.dto;

import java.time.LocalDate;

public class OperarioDTO extends TrabajadorDTO {

	private TrabajadorDTO trabajadordto;
	private int desempenio;

	public OperarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperarioDTO(LocalDate fechaIngreso, int salario, String estado, String contrasenia) {
		super(fechaIngreso, salario, estado, contrasenia);
		// TODO Auto-generated constructor stub
	}

	public OperarioDTO(Integer idPersona, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, int numeroDocumento, int telefono, String correo, String tipoDocumento) {
		super(idPersona, primerNombre, segundoNombre, primerApellido, segundoApellido, numeroDocumento, telefono,
				correo, tipoDocumento);
		// TODO Auto-generated constructor stub
	}

	public OperarioDTO(TrabajadorDTO trabajadordto, int desempenio) {
		super();
		this.trabajadordto = trabajadordto;
		this.desempenio = desempenio;
	}

	public TrabajadorDTO getTrabajadordto() {
		return trabajadordto;
	}

	public void setTrabajadordto(TrabajadorDTO trabajadordto) {
		this.trabajadordto = trabajadordto;
	}

	public int getDesempenio() {
		return desempenio;
	}

	public void setDesempenio(int desempenio) {
		this.desempenio = desempenio;
	}

	@Override
	public String toString() {
		return "OperarioDTO [trabajadordto=" + trabajadordto + ", desempenio=" + desempenio + "]";
	}

}
