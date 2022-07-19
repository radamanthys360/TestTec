package com.prueba.tec.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tec.entidades.PermisoRecurso;
import com.prueba.tec.repository.PermisoRecursoRepository;


@Service
public class PermisoRecursoServices {

	@Autowired
	private PermisoRecursoRepository<PermisoRecurso> permisoRecursoRepository;
	
	public Object[][] findPermisoRecurso() {
		return permisoRecursoRepository.findPermisoRecurso();
	}
	
}
