package co.edu.unbosque.backLuxtruck.dto;

import java.io.Serializable;
import java.util.Objects;

public class EspecialidadOperarioIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idEspecialidad;
	private Integer operarioIdPersona;

	public EspecialidadOperarioIdDTO() {
	}

	public EspecialidadOperarioIdDTO(Integer idEspecialidad, Integer operarioIdPersona) {
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
		if (!(o instanceof EspecialidadOperarioIdDTO)) return false;
		EspecialidadOperarioIdDTO that = (EspecialidadOperarioIdDTO) o;
		return Objects.equals(idEspecialidad, that.idEspecialidad) &&
				Objects.equals(operarioIdPersona, that.operarioIdPersona);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEspecialidad, operarioIdPersona);
	}

	@Override
	public String toString() {
		return "EspecialidadOperarioIdDTO [idEspecialidad=" + idEspecialidad + ", operarioIdPersona="
				+ operarioIdPersona + "]";
	}
	
	
}