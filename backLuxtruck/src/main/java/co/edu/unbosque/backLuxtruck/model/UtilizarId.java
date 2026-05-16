package co.edu.unbosque.backLuxtruck.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UtilizarId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "operario_idPersona")
	private Integer operarioIdPersona;

	@Column(name = "IdMaquina")
	private Integer idMaquina;

	public UtilizarId() {
	}

	public UtilizarId(Integer operarioIdPersona, Integer idMaquina) {
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
		if (this == o) return true;
		if (!(o instanceof UtilizarId)) return false;
		UtilizarId that = (UtilizarId) o;
		return Objects.equals(operarioIdPersona, that.operarioIdPersona) &&
				Objects.equals(idMaquina, that.idMaquina);
	}

	@Override
	public int hashCode() {
		return Objects.hash(operarioIdPersona, idMaquina);
	}
}