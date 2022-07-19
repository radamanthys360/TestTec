package com.prueba.tec.service;

import java.util.List;

import com.prueba.tec.entidades.Blogs;

public interface IBlogService {
	
	Blogs findRecordById(Long id);
	List<Blogs> findAll();
	Long save(Blogs entidad);
	void delete(Blogs entidad);

}
