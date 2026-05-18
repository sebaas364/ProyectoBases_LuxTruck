package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Vendedor;

public interface VendedorRepository extends CrudRepository<Vendedor, Integer> {

	Optional<Vendedor> findByNumeroDocumento(String numeroDocumento);

	Optional<Vendedor> findByIdPersona(Integer idPersona);

	Optional<Vendedor> findByCorreo(String correo);
}