package co.edu.unbosque.backLuxtruck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.backLuxtruck.dto.ProductoDTO;
import co.edu.unbosque.backLuxtruck.service.ProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class ProductoController {

	@Autowired
	private ProductoService productoServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody ProductoDTO nuevoProducto) {
		int estado = productoServ.create(nuevoProducto);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el producto, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(productoServ.getAll());
	}

	@PutMapping(path = "/update/{idProducto}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idProducto, @RequestBody ProductoDTO productoDTO) {
		int estado = productoServ.update(idProducto, productoDTO);
		return estado == 0
				? ResponseEntity.ok("Producto actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	}

	@DeleteMapping("/delete/{idProducto}")
	public ResponseEntity<String> delete(@PathVariable int idProducto) {
		int estado = productoServ.delete(idProducto);
		return estado == 0
				? ResponseEntity.ok("Producto eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	}

	@PostMapping("/addinventario")
	public ResponseEntity<String> addInventarioToProducto(
			@RequestParam int idProducto,
			@RequestParam int stockMinimo,
			@RequestParam int cantidadProducto) {

		int estado = productoServ.addInventarioToProducto(idProducto, stockMinimo, cantidadProducto);

		if (estado == 0) {
			return ResponseEntity.ok("Inventario agregado al producto exitosamente");
		}

		if (estado == 2) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El producto ya tiene inventario");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	}
}