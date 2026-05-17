package co.edu.unbosque.backLuxtruck.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import co.edu.unbosque.backLuxtruck.dto.EstadoTrabajadorDTO;
import co.edu.unbosque.backLuxtruck.dto.VendedorDTO;
import co.edu.unbosque.backLuxtruck.service.VendedorService;

@RestController
@RequestMapping("/vendedor")
@CrossOrigin
public class VendedorController {

	@Autowired
	private VendedorService vendedorServ;

	private Date parseDate(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		return sdf.parse(fecha);
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody VendedorDTO nuevoVendedor) {
		int estado = vendedorServ.create(nuevoVendedor);
		return estado == 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Vendedor creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el vendedor, posiblemente el ID ya está registrado");
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestParam String numeroDocumento, @RequestParam String tipoDocumento,
			@RequestParam String primerNombre, @RequestParam(required = false) String segundoNombre,
			@RequestParam String primerApellido, @RequestParam(required = false) String segundoApellido,
			@RequestParam String telefono, @RequestParam String correo, @RequestParam String fechaIngreso,
			@RequestParam Double salario, @RequestParam String contrasenia, @RequestParam Double comision,
			@RequestParam Integer idEstadoTrabajador, @RequestParam String estadoNombre) {

		try {
			Date fechaIngresoParsed = parseDate(fechaIngreso);

			EstadoTrabajadorDTO estadoDTO = new EstadoTrabajadorDTO(idEstadoTrabajador, estadoNombre);

			VendedorDTO nuevoVendedor = new VendedorDTO();
			nuevoVendedor.setNumeroDocumento(numeroDocumento);
			nuevoVendedor.setTipoDocumento(tipoDocumento);
			nuevoVendedor.setPrimerNombre(primerNombre);
			nuevoVendedor.setSegundoNombre(segundoNombre);
			nuevoVendedor.setPrimerApellido(primerApellido);
			nuevoVendedor.setSegundoApellido(segundoApellido);
			nuevoVendedor.setTelefono(telefono);
			nuevoVendedor.setCorreo(correo);
			nuevoVendedor.setFechaIngreso(fechaIngresoParsed);
			nuevoVendedor.setSalario(salario);
			nuevoVendedor.setContrasenia(contrasenia);
			nuevoVendedor.setComision(comision);

			int estado = vendedorServ.create(nuevoVendedor);
			return estado == 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Vendedor creado exitosamente")
					: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
							.body("Error al crear el vendedor, posiblemente el ID ya está registrado");

		} catch (ParseException e) {
			return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-MM-dd");
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(vendedorServ.getAll());
	}

	@PutMapping(path = "/update/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idPersona, @RequestBody VendedorDTO vendedorDTO) {
		int estado = vendedorServ.update(idPersona, vendedorDTO);
		return estado == 0 ? ResponseEntity.ok("Vendedor actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor no encontrado");
	}

	@DeleteMapping("/delete/{idPersona}")
	public ResponseEntity<String> delete(@PathVariable int idPersona) {
		int estado = vendedorServ.delete(idPersona);
		return estado == 0 ? ResponseEntity.ok("Vendedor eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor no encontrado");
	}

	@PostMapping("/addestado")
	public ResponseEntity<String> addEstadoToVendedor(@RequestParam int idPersona, @RequestParam int idEstado) {

		int estado = vendedorServ.addEstadoToVendedor(idPersona, idEstado);
		return estado == 0 ? ResponseEntity.ok("Estado agregado al vendedor exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor o estado no encontrado");
	}

	@PostMapping("/addventa")
	public ResponseEntity<String> addVentaToVendedor(@RequestParam int idVendedor, @RequestParam int idVenta) {

		int estado = vendedorServ.addVentaToVendedor(idVendedor, idVenta);
		return estado == 0 ? ResponseEntity.ok("Venta agregada al vendedor exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor o venta no encontrada");
	}

	@PostMapping("/addzona")
	public ResponseEntity<String> addZonaToVendedor(@RequestParam int idVendedor, @RequestParam int idZona) {

		int estado = vendedorServ.addZonaToVendedor(idVendedor, idZona);
		return estado == 0 ? ResponseEntity.ok("Zona agregada al vendedor exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor o zona no encontrada");
	}
}