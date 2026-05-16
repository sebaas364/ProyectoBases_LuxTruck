package co.edu.unbosque.backLuxtruck.dto;

public class UtilizarDTO {

	private UtilizarIdDTO id;
	private OperarioDTO operariodto;
	private MaquinaDTO maquinadto;

	public UtilizarDTO() {
	}

	public UtilizarDTO(UtilizarIdDTO id, OperarioDTO operariodto, MaquinaDTO maquinadto) {
		super();
		this.id = id;
		this.operariodto = operariodto;
		this.maquinadto = maquinadto;
	}

	public UtilizarIdDTO getId() {
		return id;
	}

	public void setId(UtilizarIdDTO id) {
		this.id = id;
	}

	public OperarioDTO getOperariodto() {
		return operariodto;
	}

	public void setOperariodto(OperarioDTO operariodto) {
		this.operariodto = operariodto;
	}

	public MaquinaDTO getMaquinadto() {
		return maquinadto;
	}

	public void setMaquinadto(MaquinaDTO maquinadto) {
		this.maquinadto = maquinadto;
	}

	@Override
	public String toString() {
		return "UtilizarDTO [id=" + id + ", operariodto=" + operariodto + ", maquinadto=" + maquinadto + "]";
	}

}