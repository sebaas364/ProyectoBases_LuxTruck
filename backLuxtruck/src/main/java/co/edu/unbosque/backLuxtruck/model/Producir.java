package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCIR")
public class Producir {

	@EmbeddedId
	private ProducirId id;

	@ManyToOne
	@MapsId("idMaquina")
	@JoinColumn(name = "IdMaquina")
	private Maquina maquina;

	@ManyToOne
	@MapsId("idProducto")
	@JoinColumn(name = "IdProducto")
	private Producto producto;

	@Column(name = "TiempoProduccion", nullable = false)
	private String tiempoProduccion;

	public Producir() {
	}

	public Producir(Maquina maquina, Producto producto, String tiempoProduccion) {
		this.maquina = maquina;
		this.producto = producto;
		this.tiempoProduccion = tiempoProduccion;
		this.id = new ProducirId(maquina.getIdMaquina(), producto.getIdProducto());
	}

	public ProducirId getId() {
		return id;
	}

	public void setId(ProducirId id) {
		this.id = id;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTiempoProduccion() {
		return tiempoProduccion;
	}

	public void setTiempoProduccion(String tiempoProduccion) {
		this.tiempoProduccion = tiempoProduccion;
	}
}