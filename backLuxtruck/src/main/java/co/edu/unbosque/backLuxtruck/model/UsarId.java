package co.edu.unbosque.backLuxtruck.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsarId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "IdMaterial")
	private Integer idMaterial;

	@Column(name = "IdMaquina")
	private Integer idMaquina;

	public UsarId() {
	}

	public UsarId(Integer idMaterial, Integer idMaquina) {
		this.idMaterial = idMaterial;
		this.idMaquina = idMaquina;
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Integer getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Integer idMaquina) {
		this.idMaquina = idMaquina;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UsarId)) return false;
		UsarId that = (UsarId) o;
		return Objects.equals(idMaterial, that.idMaterial) &&
				Objects.equals(idMaquina, that.idMaquina);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMaterial, idMaquina);
	}
}