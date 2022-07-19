package com.prueba.tec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.tec.entidades.PermisoRecurso;


public interface PermisoRecursoRepository <P> extends JpaRepository<PermisoRecurso,Long>{

	@Query(value = "SELECT DISTINCT(recurso), " + 
			       "(select STRING_AGG(pr2.authorities,',' ORDER BY pr2.authorities) " + 
			       "from permiso_recurso pr2 where pr2.recurso = pr1.recurso) AUTHORITIES " + 
			       "FROM permiso_recurso pr1 WHERE pr1.activo = 'S' ", 
		   nativeQuery = true)
	       Object[][] findPermisoRecurso();
}
