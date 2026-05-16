package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESPECIALIDAD_OPERARIO")
public class EspecialidadOperario {

	@EmbeddedId
	private EspecialidadOperarioId id;

	@ManyToOne
	@MapsId("idEspecialidad")
	@JoinColumn(name = "idEspecialidad")
	private Especialidad especialidad;

	@ManyToOne
	@MapsId("operarioIdPersona")
	@JoinColumn(name = "operario_idPersona")
	private Operario operario;

	public EspecialidadOperario() {
	}

	public EspecialidadOperario(Especialidad especialidad, Operario operario) {
		this.especialidad = especialidad;
		this.operario = operario;
		this.id = new EspecialidadOperarioId(especialidad.getIdEspecialidad(), operario.getIdPersona());
	}

	public EspecialidadOperarioId getId() {
		return id;
	}

	public void setId(EspecialidadOperarioId id) {
		this.id = id;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Operario getOperario() {
		return operario;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}
}