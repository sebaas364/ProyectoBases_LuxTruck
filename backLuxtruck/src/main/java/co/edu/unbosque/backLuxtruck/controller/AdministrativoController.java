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

import co.edu.unbosque.backLuxtruck.dto.AdministrativoDTO;
import co.edu.unbosque.backLuxtruck.service.AdministrativoService;

@RestController
@RequestMapping("/administrativo")
@CrossOrigin
public class AdministrativoController {

	@Autowired
	private AdministrativoService administrativoServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody AdministrativoDTO nuevoAdministrativo) {
		int estado = administrativoServ.create(nuevoAdministrativo);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Administrativo creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el administrativo, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(administrativoServ.getAll());
	}

	@PutMapping(path = "/update/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idPersona, @RequestBody AdministrativoDTO administrativoDTO) {
		int estado = administrativoServ.update(idPersona, administrativoDTO);
		return estado == 0
				? ResponseEntity.ok("Administrativo actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrativo no encontrado");
	}

	@DeleteMapping("/delete/{idPersona}")
	public ResponseEntity<String> delete(@PathVariable int idPersona) {
		int estado = administrativoServ.delete(idPersona);
		return estado == 0
				? ResponseEntity.ok("Administrativo eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrativo no encontrado");
	}

	@PostMapping("/addestado")
	public ResponseEntity<String> addEstadoToAdministrativo(
			@RequestParam int idPersona,
			@RequestParam int idEstado) {

		int estado = administrativoServ.addEstadoToAdministrativo(idPersona, idEstado);
		return estado == 0
				? ResponseEntity.ok("Estado agregado al administrativo exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrativo o estado no encontrado");
	}
}