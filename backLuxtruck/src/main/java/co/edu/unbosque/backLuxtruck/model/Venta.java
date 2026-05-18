package co.edu.unbosque.backLuxtruck.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENTA")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVenta")
	private Integer idVenta;

	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "metodoPago", nullable = false)
	private String metodoPago;

	@ManyToOne
	@JoinColumn(name = "vendedor_idPersona")
	private Vendedor vendedor;

	public Venta() {
	}

	public Venta(Integer idVenta, Date fecha, String metodoPago, Vendedor vendedor) {
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

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}