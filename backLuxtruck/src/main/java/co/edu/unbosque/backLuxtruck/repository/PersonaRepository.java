package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	boolean existsByNumeroDocumento(String numeroDocumento);
	boolean existsByCorreo(String correo);
}