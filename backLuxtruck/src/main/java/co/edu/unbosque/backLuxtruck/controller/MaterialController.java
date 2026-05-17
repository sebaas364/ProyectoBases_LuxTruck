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
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.backLuxtruck.dto.MaterialDTO;
import co.edu.unbosque.backLuxtruck.service.MaterialService;

@RestController
@RequestMapping("/material")
@CrossOrigin("*")
public class MaterialController {

	@Autowired
	private MaterialService materialServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody MaterialDTO nuevoMaterial) {
		int estado = materialServ.create(nuevoMaterial);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Material creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el material, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(materialServ.getAll());
	}

	@PutMapping(path = "/update/{idMaterial}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idMaterial, @RequestBody MaterialDTO materialDTO) {
		int estado = materialServ.update(idMaterial, materialDTO);
		return estado == 0
				? ResponseEntity.ok("Material actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no encontrado");
	}

	@DeleteMapping("/delete/{idMaterial}")
	public ResponseEntity<String> delete(@PathVariable int idMaterial) {
		int estado = materialServ.delete(idMaterial);
		return estado == 0
				? ResponseEntity.ok("Material eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no encontrado");
	}
}