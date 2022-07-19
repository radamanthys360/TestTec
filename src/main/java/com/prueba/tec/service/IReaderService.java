package com.prueba.tec.service;


import java.util.List;

import com.prueba.tec.entidades.Readers;

public interface IReaderService {
	
	Readers findRecordById(Long id);
	List<Readers> findAll();
	Long save(Readers entidad);
	void delete(Readers entidad);

}
