package co.edu.unbosque.backLuxtruck.dto;

public class ZonaVentaDTO {

	private Integer idProducto;
	private String nombreZona;	
	private VendedorDTO vendedordto;
	
	public ZonaVentaDTO() {
		// TODO Auto-generated constructor stub
	}

	public ZonaVentaDTO(Integer idProducto, String nombreZona, VendedorDTO vendedordto) {
		super();
		this.idProducto = idProducto;
		this.nombreZona = nombreZona;
		this.vendedordto = vendedordto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreZona() {
		return nombreZona;
	}

	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}


	public VendedorDTO getVendedordto() {
		return vendedordto;
	}


	public void setVendedordto(VendedorDTO vendedordto) {
		this.vendedordto = vendedordto;
	}


	@Override
	public String toString() {
		return "ZonaVentaDTO [idProducto=" + idProducto + ", nombreZona=" + nombreZona + ", vendedordto=" + vendedordto
				+ "]";
	}

	
	
}
