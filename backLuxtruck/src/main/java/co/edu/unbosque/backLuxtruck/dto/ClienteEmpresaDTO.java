package co.edu.unbosque.backLuxtruck.dto;
 
public class ClienteEmpresaDTO extends EmpresaDTO {
	private VendedorDTO vendedordto;
 
	public ClienteEmpresaDTO() {
	}

	public ClienteEmpresaDTO(VendedorDTO vendedordto) {
		super();
		this.vendedordto = vendedordto;
	}

	public ClienteEmpresaDTO(Integer idEmpresa, String nIT, String nombre, String telefono, String correo) {
		super(idEmpresa, nIT, nombre, telefono, correo);
		// TODO Auto-generated constructor stub
	}

	public VendedorDTO getVendedordto() {
		return vendedordto;
	}

	public void setVendedordto(VendedorDTO vendedordto) {
		this.vendedordto = vendedordto;
	}

	@Override
	public String toString() {
		return "ClienteEmpresaDTO [getIdEmpresa()=" + getIdEmpresa() + ", getNIT()=" + getNIT() + ", getNombre()="
				+ getNombre() + ", getTelefono()=" + getTelefono() + ", getCorreo()=" + getCorreo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
 

}