package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.backLuxtruck.model.EstadoMaquina;

public interface EstadoMaquinaRepository extends CrudRepository<EstadoMaquina, Integer> {
	boolean existsByEstado(String estado);
}