package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.ProductoVenta;
import co.edu.unbosque.backLuxtruck.model.ProductoVentaId;

public interface ProductoVentaRepository extends CrudRepository<ProductoVenta, ProductoVentaId> {
	boolean existsByCantidadProducto(Integer cantidadProducto);
}