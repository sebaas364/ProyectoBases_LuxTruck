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

import co.edu.unbosque.backLuxtruck.dto.ClienteEmpresaDTO;
import co.edu.unbosque.backLuxtruck.service.ClienteEmpresaService;

@RestController
@RequestMapping("/clienteempresa")
@CrossOrigin
public class ClienteEmpresaController {

	@Autowired
	private ClienteEmpresaService clienteEmpresaServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody ClienteEmpresaDTO nuevoClienteEmpresa) {
		int estado = clienteEmpresaServ.create(nuevoClienteEmpresa);
		return estado == 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Cliente empresa creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el cliente empresa, posiblemente el ID ya está registrado");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(clienteEmpresaServ.getAll());
	}

	@PutMapping(path = "/update/{idEmpresa}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idEmpresa,
			@RequestBody ClienteEmpresaDTO clienteEmpresaDTO) {
		int estado = clienteEmpresaServ.update(idEmpresa, clienteEmpresaDTO);
		return estado == 0 ? ResponseEntity.ok("Cliente empresa actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente empresa no encontrado");
	}

	@DeleteMapping("/delete/{idEmpresa}")
	public ResponseEntity<String> delete(@PathVariable int idEmpresa) {
		int estado = clienteEmpresaServ.delete(idEmpresa);
		return estado == 0 ? ResponseEntity.ok("Cliente empresa eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente empresa no encontrado");
	}

	@PostMapping("/addvendedor")
	public ResponseEntity<String> addVendedorToClienteEmpresa(@RequestParam int idEmpresa,
			@RequestParam int idVendedor) {

		int estado = clienteEmpresaServ.addVendedorToClienteEmpresa(idEmpresa, idVendedor);
		return estado == 0 ? ResponseEntity.ok("Vendedor agregado al cliente empresa exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente empresa o vendedor no encontrado");
	}
}