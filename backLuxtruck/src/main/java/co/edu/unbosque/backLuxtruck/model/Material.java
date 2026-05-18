package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MATERIAL")
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMaterial")
	private Integer idMaterial;

	@Column(name = "Nombre", nullable = false)
	private String nombre;

	@Column(name = "ValorUnitario", nullable = false, columnDefinition = "DECIMAL(12,2)")
	private Double valorUnitario;

	@Column(name = "CantidadDisponible", nullable = false)
	private Integer cantidadDisponible;

	@Column(name = "UnidadMedida", nullable = false)
	private String unidadMedida;

	public Material() {
	}

	public Material(Integer idMaterial, String nombre, Double valorUnitario, Integer cantidadDisponible,
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
}