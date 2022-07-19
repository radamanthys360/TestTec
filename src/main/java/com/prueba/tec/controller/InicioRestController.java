package com.prueba.tec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioRestController {
	
	@RequestMapping("/")
	public String mostrarForm(Model modelo) {
		return "inicio";
	}
	
	@GetMapping("/principal")
	public String aceptado() {
		return "principal";
	}
	
	
	@GetMapping("/denegado")
	public String denegado() {
		return "denegado";
	}

}
