package co.edu.unbosque.backLuxtruck.dto;


public class ProducirDTO {

	private ProducirIdDTO id;
	private MaquinaDTO maquinadto;
	private ProductoDTO productodto;
	private String tiempoProduccion;

	public ProducirDTO() {
	}

	public ProducirDTO(ProducirIdDTO id, MaquinaDTO maquinadto, ProductoDTO productodto, String tiempoProduccion) {
		super();
		this.id = id;
		this.maquinadto = maquinadto;
		this.productodto = productodto;
		this.tiempoProduccion = tiempoProduccion;
	}

	
	public ProducirIdDTO getId() {
		return id;
	}

	public void setId(ProducirIdDTO id) {
		this.id = id;
	}

	public MaquinaDTO getMaquinadto() {
		return maquinadto;
	}

	public void setMaquinadto(MaquinaDTO maquinadto) {
		this.maquinadto = maquinadto;
	}

	public ProductoDTO getProductodto() {
		return productodto;
	}

	public void setProductodto(ProductoDTO productodto) {
		this.productodto = productodto;
	}

	public String getTiempoProduccion() {
		return tiempoProduccion;
	}

	public void setTiempoProduccion(String tiempoProduccion) {
		this.tiempoProduccion = tiempoProduccion;
	}

	@Override
	public String toString() {
		return "ProducirDTO [id=" + id + ", maquinadto=" + maquinadto + ", productodto=" + productodto
				+ ", tiempoProduccion=" + tiempoProduccion + "]";
	}
		
}