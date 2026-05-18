package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESTADO_MAQUINA")
public class EstadoMaquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstadoMaquina")
	private Integer idEstadoMaquina;

	@Column(name = "estado")
	private String estado;

	public EstadoMaquina() {
	}

	public EstadoMaquina(Integer idEstadoMaquina, String estado) {
		this.idEstadoMaquina = idEstadoMaquina;
		this.estado = estado;
	}

	public Integer getIdEstadoMaquina() {
		return idEstadoMaquina;
	}

	public void setIdEstadoMaquina(Integer idEstadoMaquina) {
		this.idEstadoMaquina = idEstadoMaquina;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}