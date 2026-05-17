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

import co.edu.unbosque.backLuxtruck.dto.OperarioDTO;
import co.edu.unbosque.backLuxtruck.service.OperarioService;

@RestController
@RequestMapping("/operario")
@CrossOrigin("*")
public class OperarioController {

	@Autowired
	private OperarioService operarioServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody OperarioDTO nuevoOperario) {
		int estado = operarioServ.create(nuevoOperario);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Operario creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el operario, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(operarioServ.getAll());
	}

	@PutMapping(path = "/update/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idPersona, @RequestBody OperarioDTO operarioDTO) {
		int estado = operarioServ.update(idPersona, operarioDTO);
		return estado == 0
				? ResponseEntity.ok("Operario actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operario no encontrado");
	}

	@DeleteMapping("/delete/{idPersona}")
	public ResponseEntity<String> delete(@PathVariable int idPersona) {
		int estado = operarioServ.delete(idPersona);
		return estado == 0
				? ResponseEntity.ok("Operario eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operario no encontrado");
	}

	@PostMapping("/addestado")
	public ResponseEntity<String> addEstadoToOperario(
			@RequestParam int idPersona,
			@RequestParam int idEstado) {

		int estado = operarioServ.addEstadoToOperario(idPersona, idEstado);
		return estado == 0
				? ResponseEntity.ok("Estado agregado al operario exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operario o estado no encontrado");
	}

	@PostMapping("/addespecialidad")
	public ResponseEntity<String> addEspecialidadToOperario(
			@RequestParam int idOperario,
			@RequestParam int idEspecialidad) {

		int estado = operarioServ.addEspecialidadToOperario(idOperario, idEspecialidad);
		return estado == 0
				? ResponseEntity.ok("Especialidad agregada al operario exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operario o especialidad no encontrada");
	}

	@PostMapping("/addmaquina")
	public ResponseEntity<String> addMaquinaToOperario(
			@RequestParam int idOperario,
			@RequestParam int idMaquina) {

		int estado = operarioServ.addMaquinaToOperario(idOperario, idMaquina);
		return estado == 0
				? ResponseEntity.ok("Máquina agregada al operario exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operario o máquina no encontrada");
	}
}