package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.ZonaVenta;

public interface ZonaVentaRepository extends CrudRepository<ZonaVenta, Integer> {
	boolean existsByNombreZona(String nombreZona);
}