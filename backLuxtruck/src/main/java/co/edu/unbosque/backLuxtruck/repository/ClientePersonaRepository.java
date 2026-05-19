package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.ClientePersona;

public interface ClientePersonaRepository extends CrudRepository<ClientePersona, Integer> {

	Optional<ClientePersona> findByNumeroDocumento(String numeroDocumento);

	Optional<ClientePersona> findByIdPersona(Integer idPersona);
	

	Optional<ClientePersona> findByCorreo(String correo);
}