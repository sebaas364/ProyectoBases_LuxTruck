package co.edu.unbosque.backLuxtruck.dto;

import java.io.Serializable;
import java.util.Objects;


public class UsarIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idMaterial;
	private Integer idMaquina;

	public UsarIdDTO() {
	}

	public UsarIdDTO(Integer idMaterial, Integer idMaquina) {
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
		if (!(o instanceof UsarIdDTO)) return false;
		UsarIdDTO that = (UsarIdDTO) o;
		return Objects.equals(idMaterial, that.idMaterial) &&
				Objects.equals(idMaquina, that.idMaquina);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMaterial, idMaquina);
	}

	@Override
	public String toString() {
		return "UsarIdDTO [idMaterial=" + idMaterial + ", idMaquina=" + idMaquina + "]";
	}
	
}