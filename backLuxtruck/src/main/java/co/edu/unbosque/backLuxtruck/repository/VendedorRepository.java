package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Vendedor;

public interface VendedorRepository extends CrudRepository<Vendedor, Integer> {
	boolean existsByComision(Double comision);
}