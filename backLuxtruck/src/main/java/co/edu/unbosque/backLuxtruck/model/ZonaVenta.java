package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ZONA_VENTA")
public class ZonaVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idZona")
	private Integer idProducto;
	
	@Column(name="nombreZona", nullable = false )
	private String nombreZona;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_idPersona", nullable = false)
	private Vendedor vendedor;
	
	public ZonaVenta() {
		// TODO Auto-generated constructor stub
	}

	public ZonaVenta(Integer idProducto, String nombreZona, Vendedor vendedor) {
		super();
		this.idProducto = idProducto;
		this.nombreZona = nombreZona;
		this.vendedor = vendedor;
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

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	
}
