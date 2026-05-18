package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class VentaDTO {

	private Integer idVenta;
	private Date fecha;
	private String metodoPago;
	private VendedorDTO vendedordto;

	public VentaDTO() {
	}

	public VentaDTO(Integer idVenta, Date fecha, String metodoPago, VendedorDTO vendedordto) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.metodoPago = metodoPago;
		this.vendedordto = vendedordto;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public VendedorDTO getVendedordto() {
		return vendedordto;
	}

	public void setVendedordto(VendedorDTO vendedordto) {
		this.vendedordto = vendedordto;
	}

	@Override
	public String toString() {
		return "VentaDTO [idVenta=" + idVenta + ", fecha=" + fecha + ", metodoPago=" + metodoPago + ", vendedordto="
				+ vendedordto + "]";
	}
	
}