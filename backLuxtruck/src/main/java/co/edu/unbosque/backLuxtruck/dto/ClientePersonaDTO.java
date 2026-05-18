package co.edu.unbosque.backLuxtruck.dto;

public class ClientePersonaDTO extends PersonaDTO {
	private VendedorDTO vendedor;

	public ClientePersonaDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClientePersonaDTO(VendedorDTO vendedor) {
		super();
		this.vendedor = vendedor;
	}

	public ClientePersonaDTO(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo);
		// TODO Auto-generated constructor stub
	}

	public VendedorDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "ClientePersonaDTO [vendedor=" + vendedor + "]";
	}

}
