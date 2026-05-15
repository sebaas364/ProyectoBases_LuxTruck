package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INVENTARIO")
public class Inventario {

	@Id
	@Column(name = "IdProducto")
	private Integer idProducto;

	@OneToOne
	@MapsId
	@JoinColumn(name = "IdProducto")
	private Producto producto;

	@Column(name = "stockMinimo", nullable = false)
	private Integer stockMinimo;

	@Column(name = "cantidadProducto", nullable = false)
	private Integer cantidadProducto;

	public Inventario() {
		
	}

	public Inventario(Producto producto, Integer stockMinimo, Integer cantidadProducto) {
		this.producto = producto;
		this.stockMinimo = stockMinimo;
		this.cantidadProducto = cantidadProducto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
}