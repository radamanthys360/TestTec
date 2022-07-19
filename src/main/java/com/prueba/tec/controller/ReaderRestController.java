package com.prueba.tec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tec.entidades.Readers;
import com.prueba.tec.request.ReaderRequest;
import com.prueba.tec.service.IReaderService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ReaderRestController {
	
	@Autowired
	IReaderService readerService;
	
	
	@PostMapping("/reader")
	public ResponseEntity<String> guardar(@Valid @RequestBody ReaderRequest request,BindingResult bindingResult, HttpServletRequest req) throws Throwable {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Error Validacion", HttpStatus.BAD_REQUEST);
		}
		Readers entidad = parseo(request);
		readerService.save(entidad);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}
	
	@PutMapping("/reader")
	public ResponseEntity<String> actualizar(@Valid @RequestBody ReaderRequest request,BindingResult bindingResult, HttpServletRequest req) throws Throwable {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Error Validacion", HttpStatus.BAD_REQUEST);
		}
		Readers entidad = parseo(request);
		readerService.save(entidad);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}
	
	@GetMapping("/reader")
	public ResponseEntity<List<Readers>> obtenerTodos() {
			   List<Readers> findAll = readerService.findAll();

			if (findAll.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 

			return new ResponseEntity<>(findAll, HttpStatus.OK);
	}
	
	@GetMapping("/reader/{id}")
	public ResponseEntity<Readers> obtenerUno(@PathVariable("id") Long id) {
			    Readers entidad = readerService.findRecordById(id);

			if (entidad == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 

			return new ResponseEntity<>(entidad, HttpStatus.OK);
	}
	
	@DeleteMapping("/reader/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
			    Readers entidad = readerService.findRecordById(id);
			    readerService.delete(entidad);
			    
			if (entidad == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 

			return new ResponseEntity<>("ok", HttpStatus.OK);
	}
	
	
	private Readers parseo(ReaderRequest request) {
		Readers entidad = new Readers();
		entidad.setPkReader(request.getPkReaderInput());
		entidad.setName(request.getNameInput());
		return entidad;
		
	}

}
