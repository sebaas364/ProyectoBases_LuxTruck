package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Material;

public interface MaterialRepository extends CrudRepository<Material, Integer> {
	boolean existsByNombre(String nombre);
}