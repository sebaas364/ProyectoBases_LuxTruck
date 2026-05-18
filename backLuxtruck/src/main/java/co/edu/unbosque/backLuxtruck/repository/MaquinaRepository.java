package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Maquina;

public interface MaquinaRepository extends CrudRepository<Maquina, Integer> {
	boolean existsByNumeroSerie(String numeroSerie);
	boolean existsByTipo(String tipo);
}