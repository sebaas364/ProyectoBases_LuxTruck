package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Inventario;

public interface InventarioRepository extends CrudRepository<Inventario, Integer> {
	boolean existsByStockMinimo(Integer stockMinimo);
	boolean existsByCantidadProducto(Integer cantidadProducto);
}