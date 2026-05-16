package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.EstadoTrabajador;

public interface EstadoTrabajadorRepository extends CrudRepository<EstadoTrabajador, Integer>{

	Optional<EstadoTrabajador> findByIdEstadoTrabajador(Integer idEstadoTrabajador);
}
