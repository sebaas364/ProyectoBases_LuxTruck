package co.edu.unbosque.backLuxtruck.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "CLIENTE_EMPRESA")
public class ClienteEmpresa extends Empresa {
 
	@ManyToOne
	@JoinColumn(name = "vendedor_idPersona", nullable = false)
	private Vendedor vendedor;
 
	public ClienteEmpresa() {
	}
 
	public ClienteEmpresa(Vendedor vendedor) {
		super();
		this.vendedor = vendedor;
	}
 
	public ClienteEmpresa(Integer idEmpresa, String nIT, String nombre, String telefono, String correo,
			Vendedor vendedor) {
		super(idEmpresa, nIT, nombre, telefono, correo);
		this.vendedor = vendedor;
	}
 
	public Vendedor getVendedor() {
		return vendedor;
	}
 
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}