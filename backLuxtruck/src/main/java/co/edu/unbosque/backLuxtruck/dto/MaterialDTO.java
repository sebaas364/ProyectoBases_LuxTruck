package co.edu.unbosque.backLuxtruck.dto;

public class MaterialDTO {

	private Integer idMaterial;
	private String nombre;
	private Double valorUnitario;
	private Integer cantidadDisponible;
	private String unidadMedida;

	public MaterialDTO() {
	}

	public MaterialDTO(Integer idMaterial, String nombre, Double valorUnitario, Integer cantidadDisponible,
			String unidadMedida) {
		this.idMaterial = idMaterial;
		this.nombre = nombre;
		this.valorUnitario = valorUnitario;
		this.cantidadDisponible = cantidadDisponible;
		this.unidadMedida = unidadMedida;
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Override
	public String toString() {
		return "MaterialDTO [idMaterial=" + idMaterial + ", nombre=" + nombre + ", valorUnitario=" + valorUnitario
				+ ", cantidadDisponible=" + cantidadDisponible + ", unidadMedida=" + unidadMedida + "]";
	}
	
	
}