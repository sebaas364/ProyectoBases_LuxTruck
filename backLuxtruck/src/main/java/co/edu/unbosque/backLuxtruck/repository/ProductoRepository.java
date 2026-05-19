package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
	Optional<Producto> findByIdProducto(Integer idProducto);
	Optional<Producto> findByList<Producto> findByNombre(String nombre);;
}