package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Operario;

public interface OperarioRepository extends CrudRepository<Operario, Integer> {

	Optional<Operario> findByNumeroDocumento(String numeroDocumento);

	Optional<Operario> findByIdPersona(Integer idPersona);

	Optional<Operario> findByCorreo(String correo);
}