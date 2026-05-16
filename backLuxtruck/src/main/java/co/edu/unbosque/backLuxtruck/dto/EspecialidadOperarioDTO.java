package co.edu.unbosque.backLuxtruck.dto;

public class EspecialidadOperarioDTO {

	private EspecialidadOperarioIdDTO id;
	private EspecialidadDTO especialidaddto;
	private OperarioDTO operariodto;

	public EspecialidadOperarioDTO() {
	}


	public EspecialidadOperarioDTO(EspecialidadOperarioIdDTO id, EspecialidadDTO especialidaddto,
			OperarioDTO operariodto) {
		super();
		this.id = id;
		this.especialidaddto = especialidaddto;
		this.operariodto = operariodto;
	}

	public EspecialidadOperarioIdDTO getId() {
		return id;
	}


	public void setId(EspecialidadOperarioIdDTO id) {
		this.id = id;
	}


	public EspecialidadDTO getEspecialidaddto() {
		return especialidaddto;
	}

	public void setEspecialidaddto(EspecialidadDTO especialidaddto) {
		this.especialidaddto = especialidaddto;
	}

	public OperarioDTO getOperariodto() {
		return operariodto;
	}

	public void setOperariodto(OperarioDTO operariodto) {
		this.operariodto = operariodto;
	}

	@Override
	public String toString() {
		return "EspecialidadOperarioDTO [id=" + id + ", especialidaddto=" + especialidaddto + ", operariodto="
				+ operariodto + "]";
	}

}