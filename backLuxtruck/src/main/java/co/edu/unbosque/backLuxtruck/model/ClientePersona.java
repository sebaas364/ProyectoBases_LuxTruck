package co.edu.unbosque.backLuxtruck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENTE_PERSONA")
public class ClientePersona extends Persona{
	
	@ManyToOne
    @JoinColumn(name = "vendedor_idPersona", nullable = false)
    private Vendedor vendedor;
	
	
	public ClientePersona() {
		
	}

	public ClientePersona(Vendedor vendedor) {
		super();
		this.vendedor = vendedor;
	}

	public ClientePersona(Integer idPersona, String numeroDocumento, String tipoDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo,
			Vendedor vendedor) {
		super(idPersona, numeroDocumento, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,
				telefono, correo);
		this.vendedor = vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}	
	
}
