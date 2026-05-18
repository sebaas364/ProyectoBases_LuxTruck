package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Especialidad;

public interface EspecialidadRepository extends CrudRepository<Especialidad, Integer> {
	boolean existsByNombreEspecialidad(String nombreEspecialidad);
    Optional<Especialidad> findByNombreEspecialidad(String nombreEspecialidad);
}