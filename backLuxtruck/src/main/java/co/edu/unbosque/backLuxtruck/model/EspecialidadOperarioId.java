package co.edu.unbosque.backLuxtruck.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EspecialidadOperarioId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "idEspecialidad")
	private Integer idEspecialidad;

	@Column(name = "operario_idPersona")
	private Integer operarioIdPersona;

	public EspecialidadOperarioId() {
	}

	public EspecialidadOperarioId(Integer idEspecialidad, Integer operarioIdPersona) {
		this.idEspecialidad = idEspecialidad;
		this.operarioIdPersona = operarioIdPersona;
	}

	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public Integer getOperarioIdPersona() {
		return operarioIdPersona;
	}

	public void setOperarioIdPersona(Integer operarioIdPersona) {
		this.operarioIdPersona = operarioIdPersona;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EspecialidadOperarioId)) return false;
		EspecialidadOperarioId that = (EspecialidadOperarioId) o;
		return Objects.equals(idEspecialidad, that.idEspecialidad) &&
				Objects.equals(operarioIdPersona, that.operarioIdPersona);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEspecialidad, operarioIdPersona);
	}
}