package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Maquina;
import java.util.List;


public interface MaquinaRepository extends CrudRepository<Maquina, Integer> {
	boolean existsByNumeroSerie(String numeroSerie);
	boolean existsByTipo(String tipo);
	
	Optional<Maquina> findByNumeroSerie(String numeroSerie);
	Optional<Maquina> findByIdMaquina(Integer idMaquina);
	
	long countByEstadoMaquinaEstado(String estado);
}