package co.edu.unbosque.backLuxtruck.dto;

import java.math.BigDecimal;

public class ProveedorDTO extends EmpresaDTO{

	private BigDecimal calificacion;
	private String tipoProveedor;

	public ProveedorDTO() {
	}

	public ProveedorDTO(BigDecimal calificacion, String tipoProveedor) {
		this.calificacion = calificacion;
		this.tipoProveedor = tipoProveedor;
	}	
	
	public ProveedorDTO(Integer idEmpresa, String nIT, String nombre, String telefono, String correo, BigDecimal calificacion,
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

	@Override
	public String toString() {
		return "ProveedorDTO [calificacion=" + calificacion + ", tipoProveedor=" + tipoProveedor + "]";
	}
	
}