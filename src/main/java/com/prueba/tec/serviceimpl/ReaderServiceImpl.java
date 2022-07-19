package com.prueba.tec.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tec.entidades.Readers;
import com.prueba.tec.repository.IReaderRepository;
import com.prueba.tec.service.IReaderService;

@Service
public class ReaderServiceImpl implements IReaderService{
	
	@Autowired
	private IReaderRepository readerRepository;

	@Override
	public Readers findRecordById(Long id) {
		return readerRepository.findRecordById(id);
	}

	@Override
	public List<Readers> findAll() {
		return readerRepository.findAll();
	}

	@Override
	public Long save(Readers entidad) {
		Long pkReader = 0L;
		try {
			readerRepository.save(entidad).getPkReader();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pkReader;
	}

	@Override
	public void delete(Readers entidad) {
		readerRepository.delete(entidad);
		
	}

	@Override
	public List<Readers> findByName(String nombre) {
		return readerRepository.findByName(nombre);
	}

}
