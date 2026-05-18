package co.edu.unbosque.backLuxtruck.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoVentaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "IdVenta")
	private Integer idVenta;

	@Column(name = "IdProducto")
	private Integer idProducto;

	public ProductoVentaId() {
	}

	public ProductoVentaId(Integer idVenta, Integer idProducto) {
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
		if (!(o instanceof ProductoVentaId)) return false;
		ProductoVentaId that = (ProductoVentaId) o;
		return Objects.equals(idVenta, that.idVenta) &&
				Objects.equals(idProducto, that.idProducto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVenta, idProducto);
	}
}