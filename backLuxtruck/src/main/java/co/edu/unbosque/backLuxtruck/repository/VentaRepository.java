package co.edu.unbosque.backLuxtruck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.backLuxtruck.model.Venta;

public interface VentaRepository extends CrudRepository<Venta, Integer> {

	Optional<Venta> findByIdVenta(Integer idVenta);

	boolean existsByMetodoPago(String metodoPago);

	@Query("SELECT COALESCE(SUM(pv.cantidadProducto), 0) FROM ProductoVenta pv WHERE MONTH(pv.venta.fecha) = MONTH(CURRENT_DATE) AND YEAR(pv.venta.fecha) = YEAR(CURRENT_DATE)")
	Long sumVentasDelMes();
}