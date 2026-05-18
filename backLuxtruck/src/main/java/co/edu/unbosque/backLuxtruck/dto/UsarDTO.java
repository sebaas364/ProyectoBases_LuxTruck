package co.edu.unbosque.backLuxtruck.dto;

public class UsarDTO {

	private UsarIdDTO id;
	private MaterialDTO materialdto;
	private MaquinaDTO maquinadto;
	private String materialUsado;

	public UsarDTO() {
	}

	public UsarDTO(UsarIdDTO id, MaterialDTO materialdto, MaquinaDTO maquinadto, String materialUsado) {
		super();
		this.id = id;
		this.materialdto = materialdto;
		this.maquinadto = maquinadto;
		this.materialUsado = materialUsado;
	}

	public UsarIdDTO getId() {
		return id;
	}

	public void setId(UsarIdDTO id) {
		this.id = id;
	}

	public MaterialDTO getMaterialdto() {
		return materialdto;
	}

	public void setMaterialdto(MaterialDTO materialdto) {
		this.materialdto = materialdto;
	}

	public MaquinaDTO getMaquinadto() {
		return maquinadto;
	}

	public void setMaquinadto(MaquinaDTO maquinadto) {
		this.maquinadto = maquinadto;
	}

	public String getMaterialUsado() {
		return materialUsado;
	}

	public void setMaterialUsado(String materialUsado) {
		this.materialUsado = materialUsado;
	}

	@Override
	public String toString() {
		return "UsarDTO [id=" + id + ", materialdto=" + materialdto + ", maquinadto=" + maquinadto + ", materialUsado="
				+ materialUsado + "]";
	}

}