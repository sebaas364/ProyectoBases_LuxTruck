package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Inventario;

public interface InventarioRepository extends CrudRepository<Inventario, Integer> {
	boolean existsByStockMinimo(Integer stockMinimo);

	boolean existsByCantidadProducto(Integer cantidadProducto);

	@Query("SELECT SUM(cantidadProducto) FROM Inventario")
	Long sumCantidadProducto();

	@Query("SELECT COUNT(idProducto) FROM Inventario WHERE cantidadProducto < stockMinimo")
	long countStockBajo();

	Optional<Inventario> findByCantidadProductoLessThan(Integer valor);
}