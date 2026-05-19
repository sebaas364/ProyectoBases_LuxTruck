package co.edu.unbosque.backLuxtruck.dto;
 
public class ClienteEmpresaDTO extends EmpresaDTO {
	private VendedorDTO vendedor;
 
	public ClienteEmpresaDTO() {
	}

	public ClienteEmpresaDTO(VendedorDTO vendedor) {
		super();
		this.vendedor = vendedor;
	}

	public ClienteEmpresaDTO(Integer idEmpresa, String nIT, String nombre, String telefono, String correo, VendedorDTO vendedor	) {
		super(idEmpresa, nIT, nombre, telefono, correo);
		this.vendedor = vendedor;
	}

	public VendedorDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}

}