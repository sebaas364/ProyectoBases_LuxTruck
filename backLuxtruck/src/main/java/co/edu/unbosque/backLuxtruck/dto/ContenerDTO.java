package co.edu.unbosque.backLuxtruck.dto;

public class ContenerDTO {
	private ContenerIdDTO id;
	private PedidoMaterialDTO pedidoMaterialdto;
	private MaterialDTO materialdto;

	public ContenerDTO() {
	}

	public ContenerDTO(ContenerIdDTO id, PedidoMaterialDTO pedidoMaterialdto, MaterialDTO materialdto) {
		super();
		this.id = id;
		this.pedidoMaterialdto = pedidoMaterialdto;
		this.materialdto = materialdto;
	}


	public ContenerIdDTO getId() {
		return id;
	}

	public void setId(ContenerIdDTO id) {
		this.id = id;
	}

	public PedidoMaterialDTO getPedidoMaterialdto() {
		return pedidoMaterialdto;
	}

	public void setPedidoMaterialdto(PedidoMaterialDTO pedidoMaterialdto) {
		this.pedidoMaterialdto = pedidoMaterialdto;
	}


	public MaterialDTO getMaterialdto() {
		return materialdto;
	}

	public void setMaterialdto(MaterialDTO materialdto) {
		this.materialdto = materialdto;
	}

	@Override
	public String toString() {
		return "ContenerDTO [id=" + id + ", pedidoMaterialdto=" + pedidoMaterialdto + ", materialdto=" + materialdto
				+ "]";
	}
}