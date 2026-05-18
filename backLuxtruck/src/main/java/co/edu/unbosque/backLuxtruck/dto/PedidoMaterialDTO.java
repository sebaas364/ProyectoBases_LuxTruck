package co.edu.unbosque.backLuxtruck.dto;

import java.util.Date;

public class PedidoMaterialDTO {
 
     private Integer idPedido;
    private String cantidadMaterial;
    private Date fechaPedido;
    private Date fechaEntrega;
    private ProveedorDTO proveedordto;
 
    public PedidoMaterialDTO() {
    }
 
    public PedidoMaterialDTO(Integer idPedido, String cantidadMaterial, Date fechaPedido, Date fechaEntrega,
                          ProveedorDTO proveedordto) {
        this.idPedido = idPedido;
        this.cantidadMaterial = cantidadMaterial;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.proveedordto = proveedordto;
    }
 
    public Integer getIdPedido() {
        return idPedido;
    }
 
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
 
    public String getCantidadMaterial() {
        return cantidadMaterial;
    }
 
    public void setCantidadMaterial(String cantidadMaterial) {
        this.cantidadMaterial = cantidadMaterial;
    }
 
    public Date getFechaPedido() {
        return fechaPedido;
    }
 
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
 
    public Date getFechaEntrega() {
        return fechaEntrega;
    }
 
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
 
	public ProveedorDTO getProveedordto() {
		return proveedordto;
	}

	public void setProveedordto(ProveedorDTO proveedordto) {
		this.proveedordto = proveedordto;
	}

	@Override
	public String toString() {
		return "PedidoMaterialDTO [idPedido=" + idPedido + ", cantidadMaterial=" + cantidadMaterial + ", fechaPedido="
				+ fechaPedido + ", fechaEntrega=" + fechaEntrega + ", proveedordto=" + proveedordto + "]";
	}
    
}