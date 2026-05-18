package co.edu.unbosque.backLuxtruck.dto;

public class MaquinaDTO {

	private Integer idMaquina;
	private String numeroSerie;
	private String tipo;
	private EstadoMaquinaDTO estadoMaquina;

	public MaquinaDTO() {
	}

	public MaquinaDTO(Integer idMaquina, String numeroSerie, String tipo, EstadoMaquinaDTO estadoMaquina) {
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

	public EstadoMaquinaDTO getEstadoMaquina() {
		return estadoMaquina;
	}

	public void setEstadoMaquina(EstadoMaquinaDTO estadoMaquina) {
		this.estadoMaquina = estadoMaquina;
	}

	@Override
	public String toString() {
		return "MaquinaDTO [idMaquina=" + idMaquina + ", numeroSerie=" + numeroSerie + ", tipo=" + tipo + "]";
	}
	

}