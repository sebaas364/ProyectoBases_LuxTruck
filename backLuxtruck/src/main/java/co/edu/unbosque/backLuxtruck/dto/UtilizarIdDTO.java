package co.edu.unbosque.backLuxtruck.dto;

import java.io.Serializable;
import java.util.Objects;

public class UtilizarIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer operarioIdPersona;
	private Integer idMaquina;

	public UtilizarIdDTO() {
	}

	public UtilizarIdDTO(Integer operarioIdPersona, Integer idMaquina) {
		this.operarioIdPersona = operarioIdPersona;
		this.idMaquina = idMaquina;
	}

	public Integer getOperarioIdPersona() {
		return operarioIdPersona;
	}

	public void setOperarioIdPersona(Integer operarioIdPersona) {
		this.operarioIdPersona = operarioIdPersona;
	}

	public Integer getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Integer idMaquina) {
		this.idMaquina = idMaquina;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UtilizarIdDTO))
			return false;
		UtilizarIdDTO that = (UtilizarIdDTO) o;
		return Objects.equals(operarioIdPersona, that.operarioIdPersona) && Objects.equals(idMaquina, that.idMaquina);
	}

	@Override
	public int hashCode() {
		return Objects.hash(operarioIdPersona, idMaquina);
	}

	@Override
	public String toString() {
		return "UtilizarIdDTO [operarioIdPersona=" + operarioIdPersona + ", idMaquina=" + idMaquina + "]";
	}

}