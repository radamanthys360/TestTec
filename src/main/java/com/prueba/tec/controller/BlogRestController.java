package com.prueba.tec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RestController;
import com.prueba.tec.entidades.Blogs;
import com.prueba.tec.request.BlogRequest;
import com.prueba.tec.service.IBlogService;

@RestController
public class BlogRestController {
	
	@Autowired
	IBlogService blogService;
	
	@PostMapping("/blog")
	public ResponseEntity<String> guardar(@Valid @RequestBody BlogRequest request,BindingResult bindingResult, HttpServletRequest req) throws Throwable {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Error Validacion", HttpStatus.BAD_REQUEST);
		}
		Blogs entidad = parseo(request);
		blogService.save(entidad);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}
	
	@PutMapping("/blog")
	public ResponseEntity<String> actualizar(@Valid @RequestBody BlogRequest request,BindingResult bindingResult, HttpServletRequest req) throws Throwable {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Error Validacion", HttpStatus.BAD_REQUEST);
		}
		Blogs entidad = parseo(request);
		blogService.save(entidad);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}
	
	@GetMapping("/blog")
	public ResponseEntity<List<Blogs>> obtenerTodos() {
		List<Blogs> findAll = blogService.findAll();

			if (findAll.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 

			return new ResponseEntity<>(findAll, HttpStatus.OK);
	}
	
	@GetMapping("/blog/{id}")
	public ResponseEntity<Blogs> obtenerUno(@PathVariable("id") Long id) {
		Blogs entidad = blogService.findRecordById(id);

			if (entidad == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 

			return new ResponseEntity<>(entidad, HttpStatus.OK);
	}
	
	@DeleteMapping("/blog/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		Blogs entidad = blogService.findRecordById(id);
		blogService.delete(entidad);
			    
			if (entidad == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 

			return new ResponseEntity<>("ok", HttpStatus.OK);
	}
	
	
	private Blogs parseo(BlogRequest request) {
		Blogs entidad = new Blogs();
		entidad.setPkBlog(request.getPkBlogInput());
		entidad.setTitle(request.getTitleInput());
		entidad.setDescription(request.getDescriptionInput());
		return entidad;
	}

}
