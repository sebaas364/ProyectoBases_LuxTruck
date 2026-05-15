package co.edu.unbosque.backLuxtruck.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "PROVEEDOR")
public class Proveedor extends Empresa{

	@Column(name = "calificacion", columnDefinition = "NUMERIC(4)")
	private BigDecimal calificacion;

	@Column(name = "tipoProveedor", nullable = false)
	private String tipoProveedor;

	public Proveedor() {
	}

	public Proveedor(BigDecimal calificacion, String tipoProveedor) {
		this.calificacion = calificacion;
		this.tipoProveedor = tipoProveedor;
	}

	
	
	public Proveedor(Integer idEmpresa, String nIT, String nombre, String telefono, String correo, BigDecimal calificacion,
			String tipoProveedor) {
		super(idEmpresa, nIT, nombre, telefono, correo);
		this.calificacion = calificacion;
		this.tipoProveedor = tipoProveedor;
	}

	public BigDecimal getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}

	public String getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
}