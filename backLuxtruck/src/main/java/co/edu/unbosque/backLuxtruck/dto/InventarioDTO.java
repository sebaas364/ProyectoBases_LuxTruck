package co.edu.unbosque.backLuxtruck.dto;

public class InventarioDTO {

	private Integer idProducto;
	private ProductoDTO productodto;
	private Integer stockMinimo;
	private Integer cantidadProducto;

	public InventarioDTO() {
		
	}

	public InventarioDTO(ProductoDTO productodto, Integer stockMinimo, Integer cantidadProducto) {
		this.productodto = productodto;
		this.stockMinimo = stockMinimo;
		this.cantidadProducto = cantidadProducto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}


	public Integer getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public ProductoDTO getProductodto() {
		return productodto;
	}

	public void setProductodto(ProductoDTO productodto) {
		this.productodto = productodto;
	}

	@Override
	public String toString() {
		return "InventarioDTO [idProducto=" + idProducto + ", productodto=" + productodto + ", stockMinimo="
				+ stockMinimo + ", cantidadProducto=" + cantidadProducto + "]";
	}
	
}