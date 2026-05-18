package co.edu.unbosque.backLuxtruck.dto;


public class ProductoVentaDTO {

	private ProductoVentaIdDTO id;
	private VentaDTO ventadto;
	private ProductoDTO productodto;
	private Integer cantidadProducto;
	public ProductoVentaDTO() {
	}

	public ProductoVentaDTO(ProductoVentaIdDTO id, VentaDTO ventadto, ProductoDTO productodto, Integer cantidadProducto) {
		super();
		this.id = id;
		this.ventadto = ventadto;
		this.productodto = productodto;
		this.cantidadProducto = cantidadProducto;
	}

	public ProductoVentaIdDTO getId() {
		return id;
	}


	public void setId(ProductoVentaIdDTO id) {
		this.id = id;
	}


	public ProductoDTO getProductodto() {
		return productodto;
	}


	public void setProductodto(ProductoDTO productodto) {
		this.productodto = productodto;
	}


	public VentaDTO getVentadto() {
		return ventadto;
	}

	public void setVentadto(VentaDTO ventadto) {
		this.ventadto = ventadto;
	}

	
	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	@Override
	public String toString() {
		return "ProductoVentaDTO [id=" + id + ", ventadto=" + ventadto + ", productodto=" + productodto
				+ ", cantidadProducto=" + cantidadProducto + "]";
	}

}