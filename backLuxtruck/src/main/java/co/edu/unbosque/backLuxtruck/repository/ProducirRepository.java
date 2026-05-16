package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Producir;
import co.edu.unbosque.backLuxtruck.model.ProducirId;

public interface ProducirRepository extends CrudRepository<Producir, ProducirId> {
	boolean existsByTiempoProduccion(String tiempoProduccion);
}