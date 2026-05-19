package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class VentaDTO {

	private Integer idVenta;
	private Date fecha;
	private String metodoPago;
	private VendedorDTO vendedor;

	public VentaDTO() {
	}

	public VentaDTO(Integer idVenta, Date fecha, String metodoPago, VendedorDTO vendedor) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.metodoPago = metodoPago;
		this.vendedor = vendedor;
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

	public VendedorDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}

}