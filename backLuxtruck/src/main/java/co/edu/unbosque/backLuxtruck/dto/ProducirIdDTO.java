package co.edu.unbosque.backLuxtruck.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProducirIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idMaquina;

	private Integer idProducto;

	public ProducirIdDTO() {
	}

	public ProducirIdDTO(Integer idMaquina, Integer idProducto) {
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
		if (!(o instanceof ProducirIdDTO)) return false;
		ProducirIdDTO that = (ProducirIdDTO) o;
		return Objects.equals(idMaquina, that.idMaquina) &&
				Objects.equals(idProducto, that.idProducto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMaquina, idProducto);
	}

	@Override
	public String toString() {
		return "ProducirIdDTO [idMaquina=" + idMaquina + ", idProducto=" + idProducto + "]";
	}

}