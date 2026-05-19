package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Proveedor;


public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
	
	Optional<Proveedor> findByNIT(String nIT);
	
	Optional<Proveedor> findByIdEmpresa(Integer idEmpresa);
}