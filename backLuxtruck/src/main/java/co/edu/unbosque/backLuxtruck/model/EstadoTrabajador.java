package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESTADO_TRABAJADOR")
public class EstadoTrabajador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstadoTrabajador")
	private Integer idEstadoTrabajador;

	@Column(name = "estado")
	private String estado;

	public EstadoTrabajador() {
	}

	public EstadoTrabajador(Integer idEstadoTrabajador, String estado) {
		this.idEstadoTrabajador = idEstadoTrabajador;
		this.estado = estado;
	}

	public Integer getIdEstadoTrabajador() {
		return idEstadoTrabajador;
	}

	public void setIdEstadoTrabajador(Integer idEstadoTrabajador) {
		this.idEstadoTrabajador = idEstadoTrabajador;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}