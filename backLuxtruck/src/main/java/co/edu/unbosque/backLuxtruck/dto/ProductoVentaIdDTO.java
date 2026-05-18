package co.edu.unbosque.backLuxtruck.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProductoVentaIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idVenta;
	private Integer idProducto;

	public ProductoVentaIdDTO() {
	}

	public ProductoVentaIdDTO(Integer idVenta, Integer idProducto) {
		this.idVenta = idVenta;
		this.idProducto = idProducto;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductoVentaIdDTO)) return false;
		ProductoVentaIdDTO that = (ProductoVentaIdDTO) o;
		return Objects.equals(idVenta, that.idVenta) &&
				Objects.equals(idProducto, that.idProducto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVenta, idProducto);
	}

	@Override
	public String toString() {
		return "ProductoVentaIdDTO [idVenta=" + idVenta + ", idProducto=" + idProducto + "]";
	}
	
}