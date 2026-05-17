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

import co.edu.unbosque.backLuxtruck.dto.MaquinaDTO;
import co.edu.unbosque.backLuxtruck.service.MaquinaService;

@RestController
@RequestMapping("/maquina")
@CrossOrigin
public class MaquinaController {

	@Autowired
	private MaquinaService maquinaServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody MaquinaDTO nuevaMaquina) {
		int estado = maquinaServ.create(nuevaMaquina);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Máquina creada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear la máquina, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(maquinaServ.getAll());
	}

	@PutMapping(path = "/update/{idMaquina}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idMaquina, @RequestBody MaquinaDTO maquinaDTO) {
		int estado = maquinaServ.update(idMaquina, maquinaDTO);
		return estado == 0
				? ResponseEntity.ok("Máquina actualizada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Máquina no encontrada");
	}

	@DeleteMapping("/delete/{idMaquina}")
	public ResponseEntity<String> delete(@PathVariable int idMaquina) {
		int estado = maquinaServ.delete(idMaquina);
		return estado == 0
				? ResponseEntity.ok("Máquina eliminada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Máquina no encontrada");
	}

	@PostMapping("/addestado")
	public ResponseEntity<String> addEstadoToMaquina(
			@RequestParam int idMaquina,
			@RequestParam int idEstado) {

		int estado = maquinaServ.addEstadoToMaquina(idMaquina, idEstado);
		return estado == 0
				? ResponseEntity.ok("Estado agregado a la máquina exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Máquina o estado no encontrado");
	}

	@PostMapping("/addmaterial")
	public ResponseEntity<String> addMaterialToMaquina(
			@RequestParam int idMaquina,
			@RequestParam int idMaterial,
			@RequestParam String descripcion) {

		int estado = maquinaServ.addMaterialToMaquina(idMaquina, idMaterial, descripcion);
		return estado == 0
				? ResponseEntity.ok("Material agregado a la máquina exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Máquina o material no encontrado");
	}

	@PostMapping("/addproducto")
	public ResponseEntity<String> addProductoToMaquina(
			@RequestParam int idMaquina,
			@RequestParam int idProducto,
			@RequestParam String tiempoProduccion) {

		int estado = maquinaServ.addProductoToMaquina(idMaquina, idProducto, tiempoProduccion);
		return estado == 0
				? ResponseEntity.ok("Producto agregado a la máquina exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Máquina o producto no encontrado");
	}
}