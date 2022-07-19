package com.prueba.tec.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.tec.entidades.BlogsReaders;
import com.prueba.tec.repository.IBlogReaderRepository;
import com.prueba.tec.service.IBlogReaderService;

@Service
public class BlogReaderServiceImpl implements IBlogReaderService{
	
	@Autowired
	private IBlogReaderRepository blogReaderRepository;

	@Override
	public List<BlogsReaders> findAll() {
		return blogReaderRepository.findAll();
	}

	@Override
	public void save(BlogsReaders entidad) {
		blogReaderRepository.save(entidad);
	}

	@Override
	public void delete(BlogsReaders entidad) {
		blogReaderRepository.delete(entidad);
	}

	@Override
	public BlogsReaders findRecordById(Long idBlog, Long idReader) {
		return blogReaderRepository.findRecordById(idBlog, idReader);
	}

}
