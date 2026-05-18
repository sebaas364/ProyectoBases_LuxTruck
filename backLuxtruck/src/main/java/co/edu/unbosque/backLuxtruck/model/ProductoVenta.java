package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO_VENTA")
public class ProductoVenta {

	@EmbeddedId
	private ProductoVentaId id;

	@ManyToOne
	@MapsId("idVenta")
	@JoinColumn(name = "IdVenta")
	private Venta venta;

	@ManyToOne
	@MapsId("idProducto")
	@JoinColumn(name = "IdProducto")
	private Producto producto;

	@Column(name = "CantidadProducto", nullable = false)
	private Integer cantidadProducto;

	public ProductoVenta() {
	}

	public ProductoVenta(Venta venta, Producto producto, Integer cantidadProducto) {
		this.venta = venta;
		this.producto = producto;
		this.cantidadProducto = cantidadProducto;
		this.id = new ProductoVentaId(venta.getIdVenta(), producto.getIdProducto());
	}

	public ProductoVentaId getId() {
		return id;
	}

	public void setId(ProductoVentaId id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
}