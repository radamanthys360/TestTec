package com.prueba.tec.service;

import java.util.List;
import com.prueba.tec.entidades.BlogsReaders;

public interface IBlogReaderService {
	
	List<BlogsReaders> findAll();
	void save(BlogsReaders entidad);
	void delete(BlogsReaders entidad);
	BlogsReaders findRecordById( Long idBlog, Long idReader );

}
