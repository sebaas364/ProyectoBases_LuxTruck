package co.edu.unbosque.backLuxtruck.dto;

public class EstadoTrabajadorDTO {
	
	private Integer idEstadoTrabajador;
	private String estado;

	public EstadoTrabajadorDTO() {
	}

	public EstadoTrabajadorDTO(Integer idEstadoTrabajador, String estado) {
		this.idEstadoTrabajador = idEstadoTrabajador;
		this.estado = estado;
	}

	public Integer getIdEstadoTrabajador() {
		return idEstadoTrabajador;
	}

	public void setIdEstadoTrabajador(Integer idEstadoTrabajador) {
		this.idEstadoTrabajador = idEstadoTrabajador;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EstadoTrabajadorDTO [idEstadoTrabajador=" + idEstadoTrabajador + ", estado=" + estado + "]";
	}
	
}