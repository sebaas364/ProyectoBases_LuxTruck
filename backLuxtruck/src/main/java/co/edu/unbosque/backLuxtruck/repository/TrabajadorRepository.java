package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Trabajador;

public interface TrabajadorRepository extends CrudRepository<Trabajador, Integer> {
	Optional<Trabajador> findByNumeroDocumento(String numeroDocumento);

	Optional<Trabajador> findByIdPersona(Integer idPersona);
	

	Optional<Trabajador> findByCorreo(String correo);
}