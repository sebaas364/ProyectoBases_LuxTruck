package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Operario;

public interface OperarioRepository extends CrudRepository<Operario, Integer> {
	boolean existsByDesempenio(int desempenio);
}