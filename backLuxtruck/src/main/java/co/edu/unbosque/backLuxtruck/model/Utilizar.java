package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "UTILIZAR")
public class Utilizar {

	@EmbeddedId
	private UtilizarId id;

	@ManyToOne
	@MapsId("operarioIdPersona")
	@JoinColumn(name = "operario_idPersona")
	private Operario operario;

	@ManyToOne
	@MapsId("idMaquina")
	@JoinColumn(name = "IdMaquina")
	private Maquina maquina;

	public Utilizar() {
	}

	public Utilizar(Operario operario, Maquina maquina) {
		this.operario = operario;
		this.maquina = maquina;
		this.id = new UtilizarId(operario.getIdPersona(), maquina.getIdMaquina());
	}

	public UtilizarId getId() {
		return id;
	}

	public void setId(UtilizarId id) {
		this.id = id;
	}

	public Operario getOperario() {
		return operario;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}
}