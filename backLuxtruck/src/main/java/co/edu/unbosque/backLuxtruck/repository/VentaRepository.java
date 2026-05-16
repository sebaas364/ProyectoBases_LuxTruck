package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Venta;

public interface VentaRepository extends CrudRepository<Venta, Integer> {
	boolean existsByMetodoPago(String metodoPago);
}