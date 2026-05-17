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

import co.edu.unbosque.backLuxtruck.dto.PedidoMaterialDTO;
import co.edu.unbosque.backLuxtruck.service.PedidoMaterialService;

@RestController
@RequestMapping("/pedidomaterial")
@CrossOrigin("*")
public class PedidoMaterialController {

	@Autowired
	private PedidoMaterialService pedidoMaterialServ;

	private Date parseDate(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		return sdf.parse(fecha);
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createWithJSON(@RequestBody PedidoMaterialDTO nuevoPedidoMaterial) {
		int estado = pedidoMaterialServ.create(nuevoPedidoMaterial);
		return estado == 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Pedido de material creado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error al crear el pedido de material, posiblemente el ID ya está registrado");
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestParam int idPedido, @RequestParam String cantidadMaterial,
			@RequestParam String fechaPedido, @RequestParam(required = false) String fechaEntrega) {

		try {
			Date fechaPedidoParsed = parseDate(fechaPedido);
			Date fechaEntregaParsed = null;

			if (fechaEntrega != null && !fechaEntrega.trim().isEmpty()) {
				fechaEntregaParsed = parseDate(fechaEntrega);
			}

			PedidoMaterialDTO nuevoPedidoMaterial = new PedidoMaterialDTO();
			nuevoPedidoMaterial.setIdPedido(idPedido);
			nuevoPedidoMaterial.setCantidadMaterial(cantidadMaterial);
			nuevoPedidoMaterial.setFechaPedido(fechaPedidoParsed);
			nuevoPedidoMaterial.setFechaEntrega(fechaEntregaParsed);

			int estado = pedidoMaterialServ.create(nuevoPedidoMaterial);
			return estado == 0
					? ResponseEntity.status(HttpStatus.CREATED).body("Pedido de material creado exitosamente")
					: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
							.body("Error al crear el pedido de material, posiblemente el ID ya está registrado");

		} catch (ParseException e) {
			return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-MM-dd");
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(pedidoMaterialServ.getAll());
	}

	@PutMapping(path = "/update/{idPedido}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable int idPedido, @RequestBody PedidoMaterialDTO pedidoMaterialDTO) {
		int estado = pedidoMaterialServ.update(idPedido, pedidoMaterialDTO);
		return estado == 0 ? ResponseEntity.ok("Pedido de material actualizado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido de material no encontrado");
	}

	@DeleteMapping("/delete/{idPedido}")
	public ResponseEntity<String> delete(@PathVariable int idPedido) {
		int estado = pedidoMaterialServ.delete(idPedido);
		return estado == 0 ? ResponseEntity.ok("Pedido de material eliminado exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido de material no encontrado");
	}

	@PostMapping("/addproveedor")
	public ResponseEntity<String> addProveedorToPedido(@RequestParam int idPedido, @RequestParam int idProveedor) {

		int estado = pedidoMaterialServ.addProveedorToPedido(idPedido, idProveedor);
		return estado == 0 ? ResponseEntity.ok("Proveedor agregado al pedido exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido o proveedor no encontrado");
	}

	@PostMapping("/addmaterial")
	public ResponseEntity<String> addMaterialToPedido(@RequestParam int idPedido, @RequestParam int idMaterial) {

		int estado = pedidoMaterialServ.addMaterialToPedido(idPedido, idMaterial);
		return estado == 0 ? ResponseEntity.ok("Material agregado al pedido exitosamente")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido o material no encontrado");
	}
}