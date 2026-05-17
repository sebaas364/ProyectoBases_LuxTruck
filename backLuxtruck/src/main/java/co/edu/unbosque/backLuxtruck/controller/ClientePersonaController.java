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

import co.edu.unbosque.backLuxtruck.dto.ClientePersonaDTO;
import co.edu.unbosque.backLuxtruck.service.ClientePersonaService;

@RestController
@RequestMapping("/clientepersona")
@CrossOrigin("*")
public class ClientePersonaController {

	@Autowired
	private ClientePersonaService clientePersonaServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody ClientePersonaDTO nuevoClientePersona) {
		int estado = clientePersonaServ.create(nuevoClientePersona);
		return estado == 0
				? ResponseEntity.status(HttpStatus.CREATED).body("Cliente persona creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el cliente persona, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(clientePersonaServ.getAll());
	}

	@PutMapping(path = "/update/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idPersona, @RequestBody ClientePersonaDTO clientePersonaDTO) {
		int estado = clientePersonaServ.update(idPersona, clientePersonaDTO);
		return estado == 0
				? ResponseEntity.ok("Cliente persona actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente persona no encontrado");
	}

	@DeleteMapping("/delete/{idPersona}")
	public ResponseEntity<String> delete(@PathVariable int idPersona) {
		int estado = clientePersonaServ.delete(idPersona);
		return estado == 0
				? ResponseEntity.ok("Cliente persona eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente persona no encontrado");
	}

	@PostMapping("/addvendedor")
	public ResponseEntity<String> addVendedorToClientePersona(
			@RequestParam int idPersona,
			@RequestParam int idVendedor) {

		int estado = clientePersonaServ.addVendedorToClientePersona(idPersona, idVendedor);
		return estado == 0
				? ResponseEntity.ok("Vendedor agregado al cliente persona exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente persona o vendedor no encontrado");
	}
}