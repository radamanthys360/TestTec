package com.prueba.tec.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tec.entidades.Blogs;
import com.prueba.tec.repository.IBlogRepository;
import com.prueba.tec.service.IBlogService;

@Service
public class BlogServiceImpl implements IBlogService{
	
	@Autowired
	private IBlogRepository blogRepository;

	@Override
	public Blogs findRecordById(Long id) {
		return blogRepository.findRecordById(id);
	}

	@Override
	public List<Blogs> findAll() {
		return blogRepository.findAll();
	}

	@Override
	public Long save(Blogs entidad) {
		Long pkBlog = blogRepository.save(entidad).getPkBlog();
		return pkBlog;
	}

	@Override
	public void delete(Blogs entidad) {
		blogRepository.delete(entidad);
	}

}
