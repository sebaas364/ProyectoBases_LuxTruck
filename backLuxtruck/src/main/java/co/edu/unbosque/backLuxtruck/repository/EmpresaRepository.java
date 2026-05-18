package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Integer> {
	boolean existsByNIT(String NIT);
	boolean existsByCorreo(String correo);
}
