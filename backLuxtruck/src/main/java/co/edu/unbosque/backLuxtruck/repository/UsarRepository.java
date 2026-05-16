package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Usar;
import co.edu.unbosque.backLuxtruck.model.UsarId;

public interface UsarRepository extends CrudRepository<Usar, UsarId> {
	boolean existsByMaterialUsado(String materialUsado);
}