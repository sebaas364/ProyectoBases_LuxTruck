package co.edu.unbosque.backLuxtruck.model;
 
import java.sql.Date;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "PEDIDO_MATERIAL")
public class PedidoMaterial {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private Integer idPedido;
 
    @Column(name = "cantidadMaterial")
    private String cantidadMaterial;
 
    @Column(name = "fechaPedido")
    private Date fechaPedido;
 
    @Column(name = "fechaEntrega")
    private Date fechaEntrega;
 
    @ManyToOne
    @JoinColumn(name = "proveedor_idEmpresa", nullable = false)
    private Proveedor proveedor;
 
    public PedidoMaterial() {
    }
 
    public PedidoMaterial(Integer idPedido, String cantidadMaterial, Date fechaPedido, Date fechaEntrega,
                          Proveedor proveedor) {
        this.idPedido = idPedido;
        this.cantidadMaterial = cantidadMaterial;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.proveedor = proveedor;
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
 
    public Proveedor getProveedor() {
        return proveedor;
    }
 
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}