package co.edu.unbosque.backLuxtruck.dto;

import java.io.Serializable;
import java.util.Objects;
public class ContenerIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idPedido;
	private Integer idMaterial;

	public ContenerIdDTO() {
	}

	public ContenerIdDTO(Integer idPedido, Integer idMaterial) {
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
		if (!(o instanceof ContenerIdDTO)) return false;
		ContenerIdDTO that = (ContenerIdDTO) o;
		return Objects.equals(idPedido, that.idPedido) &&
				Objects.equals(idMaterial, that.idMaterial);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido, idMaterial);
	}
}