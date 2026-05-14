package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Trabajador;

public interface TrabajadorRepository extends CrudRepository<Trabajador, Integer> {
	boolean existsByContrasenia(String contrasenia);

	Optional<Trabajador> findByNumeroDocumento(int numeroDocumento);

	Optional<Trabajador> findByCorreoIgnoreCase(String correo);
}