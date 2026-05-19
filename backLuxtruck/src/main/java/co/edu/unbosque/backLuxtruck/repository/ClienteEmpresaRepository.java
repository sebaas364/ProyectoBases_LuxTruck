package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.ClienteEmpresa;

public interface ClienteEmpresaRepository extends CrudRepository<ClienteEmpresa, Integer> {

	Optional<ClienteEmpresa> findByNIT(String nIT);
	
	Optional<ClienteEmpresa> findByIdEmpresa(Integer idEmpresa);
}