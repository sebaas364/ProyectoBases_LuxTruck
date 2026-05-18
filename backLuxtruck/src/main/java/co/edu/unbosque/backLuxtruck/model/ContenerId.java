package co.edu.unbosque.backLuxtruck.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContenerId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "idPedido")
	private Integer idPedido;

	@Column(name = "IdMaterial")
	private Integer idMaterial;

	public ContenerId() {
	}

	public ContenerId(Integer idPedido, Integer idMaterial) {
		this.idPedido = idPedido;
		this.idMaterial = idMaterial;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ContenerId)) return false;
		ContenerId that = (ContenerId) o;
		return Objects.equals(idPedido, that.idPedido) &&
				Objects.equals(idMaterial, that.idMaterial);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido, idMaterial);
	}
}