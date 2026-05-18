package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTENER")
public class Contener {

	@EmbeddedId
	private ContenerId id;

	@ManyToOne
	@MapsId("idPedido")
	@JoinColumn(name = "idPedido")
	private PedidoMaterial pedidoMaterial;

	@ManyToOne
	@MapsId("idMaterial")
	@JoinColumn(name = "IdMaterial")
	private Material material;

	public Contener() {
	}

	public Contener(PedidoMaterial pedidoMaterial, Material material) {
		this.pedidoMaterial = pedidoMaterial;
		this.material = material;
		this.id = new ContenerId(pedidoMaterial.getIdPedido(), material.getIdMaterial());
	}

	public ContenerId getId() {
		return id;
	}

	public void setId(ContenerId id) {
		this.id = id;
	}

	public PedidoMaterial getPedidoMaterial() {
		return pedidoMaterial;
	}

	public void setPedidoMaterial(PedidoMaterial pedidoMaterial) {
		this.pedidoMaterial = pedidoMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}