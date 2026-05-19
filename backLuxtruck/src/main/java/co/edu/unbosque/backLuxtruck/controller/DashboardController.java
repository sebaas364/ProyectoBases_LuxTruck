package co.edu.unbosque.backLuxtruck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.backLuxtruck.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

	 @Autowired
	    private DashboardService dashboardServ;

	    @GetMapping("/resumen")
	    public ResponseEntity<?> getResumen() {
	        return ResponseEntity.ok(dashboardServ.getResumen());
	    }
}
