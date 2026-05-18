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

import co.edu.unbosque.backLuxtruck.dto.VentaDTO;
import co.edu.unbosque.backLuxtruck.service.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin("*")
public class VentaController {

	@Autowired
	private VentaService ventaServ;

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody VentaDTO nuevaVenta) {
		int estado = ventaServ.create(nuevaVenta);
		return estado == 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Venta creada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear la venta, posiblemente el ID ya está registrado");
	}
	private Date parseDate(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		return sdf.parse(fecha);
	}
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestParam int idVenta, @RequestParam String fecha,
			@RequestParam String metodoPago) {

		try {
			Date fechaParsed = parseDate(fecha);

			VentaDTO nuevaVenta = new VentaDTO();
			nuevaVenta.setIdVenta(idVenta);
			nuevaVenta.setFecha(fechaParsed);
			nuevaVenta.setMetodoPago(metodoPago);

			int estado = ventaServ.create(nuevaVenta);
			return estado == 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Venta creada exitosamente")
					: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
							.body("Error al c rear la venta, posiblemente el ID ya está registrado");

		} catch (ParseException e) {
			return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-MM-dd");
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(ventaServ.getAll());
	}

	@PutMapping(path = "/update/{idVenta}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idVenta, @RequestBody VentaDTO ventaDTO) {
		int estado = ventaServ.update(idVenta, ventaDTO);
		return estado == 0 ? ResponseEntity.ok("Venta actualizada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
	}

	@DeleteMapping("/delete/{idVenta}")
	public ResponseEntity<String> delete(@PathVariable int idVenta) {
		int estado = ventaServ.delete(idVenta);
		return estado == 0 ? ResponseEntity.ok("Venta eliminada exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
	}

	@PostMapping("/addvendedor")
	public ResponseEntity<String> addVendedorToVenta(@RequestParam int idVenta, @RequestParam int idVendedor) {

		int estado = ventaServ.addVendedorToVenta(idVenta, idVendedor);
		return estado == 0 ? ResponseEntity.ok("Vendedor agregado a la venta exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta o vendedor no encontrado");
	}

	@PostMapping("/addproducto")
	public ResponseEntity<String> addProductoToVenta(@RequestParam int idVenta, @RequestParam int idProducto,
			@RequestParam int cantidad) {

		int estado = ventaServ.addProductoToVenta(idVenta, idProducto, cantidad);
		return estado == 0 ? ResponseEntity.ok("Producto agregado a la venta exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta o producto no encontrado");
	}
}