package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "USAR")
public class Usar {

	@EmbeddedId
	private UsarId id;

	@ManyToOne
	@MapsId("idMaterial")
	@JoinColumn(name = "IdMaterial")
	private Material material;

	@ManyToOne
	@MapsId("idMaquina")
	@JoinColumn(name = "IdMaquina")
	private Maquina maquina;

	@Column(name = "MaterialUsado", nullable = false)
	private String materialUsado;

	public Usar() {
	}

	public Usar(Material material, Maquina maquina, String materialUsado) {
		this.material = material;
		this.maquina = maquina;
		this.materialUsado = materialUsado;
		this.id = new UsarId(material.getIdMaterial(), maquina.getIdMaquina());
	}

	public UsarId getId() {
		return id;
	}

	public void setId(UsarId id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public String getMaterialUsado() {
		return materialUsado;
	}

	public void setMaterialUsado(String materialUsado) {
		this.materialUsado = materialUsado;
	}
}