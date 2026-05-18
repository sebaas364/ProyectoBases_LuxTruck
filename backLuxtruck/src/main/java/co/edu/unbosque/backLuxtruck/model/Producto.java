package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProducto")
	private Integer idProducto;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "precioUnitario", nullable = false, columnDefinition = "DECIMAL(12,2)")
	private Double precioUnitario;

	@Column(name = "tipo", nullable = false)
	private String tipo;

	public Producto() {
	}

	public Producto(Integer idProducto, String nombre, Double precioUnitario, String tipo) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.tipo = tipo;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}