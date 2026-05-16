package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.EstadoTrabajador;

public interface EspecialidadRepository extends CrudRepository<EstadoTrabajador, Integer> {
	boolean existsByEstado(String estado);
}