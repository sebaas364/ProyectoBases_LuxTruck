package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MAQUINA")
public class Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMaquina")
	private Integer idMaquina;

	@Column(name = "numeroSerie", nullable = false, unique = true)
	private String numeroSerie;

	@Column(name = "tipo", nullable = false)
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "idEstadoMaquina", nullable = false)
	private EstadoMaquina estadoMaquina;

	public Maquina() {
	}

	public Maquina(Integer idMaquina, String numeroSerie, String tipo, EstadoMaquina estadoMaquina) {
		this.idMaquina = idMaquina;
		this.numeroSerie = numeroSerie;
		this.tipo = tipo;
		this.estadoMaquina = estadoMaquina;
	}

	public Integer getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Integer idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public EstadoMaquina getEstadoMaquina() {
		return estadoMaquina;
	}

	public void setEstadoMaquina(EstadoMaquina estadoMaquina) {
		this.estadoMaquina = estadoMaquina;
	}
}