package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
	boolean existsByTipoProveedor(String tipoProveedor);
}