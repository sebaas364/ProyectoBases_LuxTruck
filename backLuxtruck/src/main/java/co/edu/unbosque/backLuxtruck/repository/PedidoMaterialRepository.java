package co.edu.unbosque.backLuxtruck.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.PedidoMaterial;

public interface PedidoMaterialRepository extends CrudRepository<PedidoMaterial, Integer> {
	boolean existsByCantidadMaterial(String cantidadMaterial);
}