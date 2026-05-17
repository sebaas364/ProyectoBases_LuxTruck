package co.edu.unbosque.backLuxtruck.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import co.edu.unbosque.backLuxtruck.dto.EstadoTrabajadorDTO;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.service.TrabajadorService;

@RestController
@RequestMapping("/trabajador")
@CrossOrigin("*")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorServ;
	
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody TrabajadorDTO newTrabajador) {
		
		
	    int status = trabajadorServ.create(newTrabajador);
	    return status == 0
	        ? ResponseEntity.status(HttpStatus.CREATED).body("Trabajador creado exitosamente")
	        : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
	              .body("Error al crear el trabajador, posiblemente el ID ya está registrado");
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> create(
	        @RequestParam String numeroDocumento,
	        @RequestParam String tipoDocumento,
	        @RequestParam String primerNombre,
	        @RequestParam(required = false) String segundoNombre,
	        @RequestParam String primerApellido,
	        @RequestParam(required = false) String segundoApellido,
	        @RequestParam String telefono,
	        @RequestParam String correo,
	        @RequestParam Double salario,
	        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIngreso,
	        @RequestParam String contrasenia,
	        @RequestParam Integer idEstadoTrabajador,
	        @RequestParam String estadoNombre) {

	    EstadoTrabajadorDTO estadoDTO = new EstadoTrabajadorDTO(idEstadoTrabajador, estadoNombre);
	    TrabajadorDTO newTrabajador = new TrabajadorDTO(
	            null, numeroDocumento, tipoDocumento,
	            primerNombre, segundoNombre,
	            primerApellido, segundoApellido,
	            telefono, correo,
	            fechaIngreso, salario, contrasenia, estadoDTO
	    );

	    int status = trabajadorServ.create(newTrabajador);
	    return status == 0
	        ? ResponseEntity.status(HttpStatus.CREATED).body("Trabajador creado exitosamente")
	        : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
	              .body("Error al crear el trabajador, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(trabajadorServ.getAll());
	}

	@PutMapping(path = "/update/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idPersona, @RequestBody TrabajadorDTO trabajadorDTO) {
		int estado = trabajadorServ.update(idPersona, trabajadorDTO);
		return estado == 0 ? ResponseEntity.ok("Trabajador actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado");
	}

	@DeleteMapping("/delete/{idPersona}")
	public ResponseEntity<String> delete(@PathVariable int idPersona) {
		int estado = trabajadorServ.delete(idPersona);
		return estado == 0 ? ResponseEntity.ok("Trabajador eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado");
	}
}