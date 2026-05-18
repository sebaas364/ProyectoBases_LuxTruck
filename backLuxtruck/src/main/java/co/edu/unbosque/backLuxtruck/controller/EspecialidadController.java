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

import co.edu.unbosque.backLuxtruck.dto.EspecialidadDTO;
import co.edu.unbosque.backLuxtruck.service.EspecialidadService;

@RestController
@RequestMapping("/especialidad")
@CrossOrigin("*")
public class EspecialidadController {

	@Autowired
	private EspecialidadService especialidadServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody EspecialidadDTO nuevaEspecialidad) {
		int estado = especialidadServ.create(nuevaEspecialidad);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Especialidad creada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear la especialidad, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(especialidadServ.getAll());
	}

	@PutMapping(path = "/update/{idEspecialidad}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idEspecialidad, @RequestBody EspecialidadDTO especialidadDTO) {
		int estado = especialidadServ.update(idEspecialidad, especialidadDTO);
		return estado == 0
				? ResponseEntity.ok("Especialidad actualizada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidad no encontrada");
	}

	@DeleteMapping("/delete/{idEspecialidad}")
	public ResponseEntity<String> delete(@PathVariable int idEspecialidad) {
		int estado = especialidadServ.delete(idEspecialidad);
		return estado == 0
				? ResponseEntity.ok("Especialidad eliminada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidad no encontrada");
	}
}