package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Utilizar;
import co.edu.unbosque.backLuxtruck.model.UtilizarId;

public interface UtilizarRepository extends CrudRepository<Utilizar, UtilizarId> {

	Optional<Utilizar> findByMaquinaIdMaquina(Integer idMaquina);
}