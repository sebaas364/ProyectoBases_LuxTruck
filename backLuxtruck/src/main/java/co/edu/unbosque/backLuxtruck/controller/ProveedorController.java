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

import co.edu.unbosque.backLuxtruck.dto.ProveedorDTO;
import co.edu.unbosque.backLuxtruck.service.ProveedorService;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody ProveedorDTO nuevoProveedor) {
		int estado = proveedorServ.create(nuevoProveedor);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el proveedor, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(proveedorServ.getAll());
	}

	@PutMapping(path = "/update/{idEmpresa}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idEmpresa, @RequestBody ProveedorDTO proveedorDTO) {
		int estado = proveedorServ.update(idEmpresa, proveedorDTO);
		return estado == 0
				? ResponseEntity.ok("Proveedor actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
	}

	@DeleteMapping("/delete/{idEmpresa}")
	public ResponseEntity<String> delete(@PathVariable int idEmpresa) {
		int estado = proveedorServ.delete(idEmpresa);
		return estado == 0
				? ResponseEntity.ok("Proveedor eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
	}

	@PostMapping("/addpedido")
	public ResponseEntity<String> addPedidoToProveedor(
			@RequestParam int idEmpresa,
			@RequestParam int idPedido) {

		int estado = proveedorServ.addPedidoToProveedor(idEmpresa, idPedido);
		return estado == 0
				? ResponseEntity.ok("Pedido agregado al proveedor exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor o pedido no encontrado");
	}
}