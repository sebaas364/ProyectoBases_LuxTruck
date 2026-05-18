package co.edu.unbosque.backLuxtruck.dto;

public class ProductoDTO {

	private Integer idProducto;
	private String nombre;
	private Double precioUnitario;
	private String tipo;

	public ProductoDTO() {
	}

	public ProductoDTO(Integer idProducto, String nombre, Double precioUnitario, String tipo) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.tipo = tipo;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ProductoDTO [idProducto=" + idProducto + ", nombre=" + nombre + ", precioUnitario=" + precioUnitario
				+ ", tipo=" + tipo + "]";
	}

}