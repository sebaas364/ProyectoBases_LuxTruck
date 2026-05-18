package co.edu.unbosque.backLuxtruck.dto;

public class EstadoMaquinaDTO{
	
	private Integer idEstadoMaquina;
	private String estado;

	public EstadoMaquinaDTO() {
	}

	public EstadoMaquinaDTO(Integer idEstadoMaquina, String estado) {
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

	@Override
	public String toString() {
		return "EstadoMaquinaDTO [idEstadoMaquina=" + idEstadoMaquina + ", estado=" + estado + "]";
	}
	
}