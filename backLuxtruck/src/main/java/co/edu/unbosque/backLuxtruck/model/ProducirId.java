package co.edu.unbosque.backLuxtruck.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProducirId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "IdMaquina")
	private Integer idMaquina;

	@Column(name = "IdProducto")
	private Integer idProducto;

	public ProducirId() {
	}

	public ProducirId(Integer idMaquina, Integer idProducto) {
		this.idMaquina = idMaquina;
		this.idProducto = idProducto;
	}

	public Integer getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Integer idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProducirId)) return false;
		ProducirId that = (ProducirId) o;
		return Objects.equals(idMaquina, that.idMaquina) &&
				Objects.equals(idProducto, that.idProducto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMaquina, idProducto);
	}
}