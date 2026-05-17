package co.edu.unbosque.backLuxtruck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.backLuxtruck.dto.LoginDTO;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.service.TrabajadorService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private TrabajadorService trabajadorServ;

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
		TrabajadorDTO trabajador = trabajadorServ.login(loginDTO);

		if (trabajador != null) {
			return ResponseEntity.ok(trabajador);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contrasenia incorrectos");
	}
}