package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Contener;
import co.edu.unbosque.backLuxtruck.model.ContenerId;

public interface ContenerRepository extends CrudRepository<Contener, ContenerId> {

}