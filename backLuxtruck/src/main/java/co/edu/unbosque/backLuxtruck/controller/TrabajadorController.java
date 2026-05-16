package co.edu.unbosque.backLuxtruck.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.backLuxtruck.dto.EstadoTrabajadorDTO;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.service.TrabajadorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/trabajador")
@CrossOrigin
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorServ;
	
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody TrabajadorDTO newTrabajador) {
	    if (newTrabajador.getPrimerNombre().contains("<") || newTrabajador.getPrimerNombre().contains(">")) {
	        return ResponseEntity.badRequest().body("Solicitud con caracteres inválidos");
	    }
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
	        @RequestParam String contrasenia,
	        @RequestParam Integer idEstadoTrabajador,
	        @RequestParam String estadoNombre) {

	    EstadoTrabajadorDTO estadoDTO = new EstadoTrabajadorDTO(idEstadoTrabajador, estadoNombre);
	    TrabajadorDTO newTrabajador = new TrabajadorDTO(
	            null, numeroDocumento, tipoDocumento,
	            primerNombre, segundoNombre,
	            primerApellido, segundoApellido,
	            telefono, correo,
	            new Date(), salario, estadoNombre, contrasenia, estadoDTO
	    );

	    int status = trabajadorServ.create(newTrabajador);
	    return status == 0
	        ? ResponseEntity.status(HttpStatus.CREATED).body("Trabajador creado exitosamente")
	        : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
	              .body("Error al crear el trabajador, posiblemente el ID ya está registrado");
	}
}
