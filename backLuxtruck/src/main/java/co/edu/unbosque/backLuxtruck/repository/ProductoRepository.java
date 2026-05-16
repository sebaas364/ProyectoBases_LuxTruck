package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
	boolean existsByNombre(String nombre);
	boolean existsByTipo(String tipo);
}