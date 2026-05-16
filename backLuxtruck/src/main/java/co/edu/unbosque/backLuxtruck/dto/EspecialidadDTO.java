package co.edu.unbosque.backLuxtruck.dto;


public class EspecialidadDTO {

	private Integer idEspecialidad;
	private String nombreEspecialidad;

	public EspecialidadDTO() {
	}

	public EspecialidadDTO(Integer idEspecialidad, String nombreEspecialidad) {
		this.idEspecialidad = idEspecialidad;
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	@Override
	public String toString() {
		return "EspecialidadDTO [idEspecialidad=" + idEspecialidad + ", nombreEspecialidad=" + nombreEspecialidad + "]";
	}
	
}