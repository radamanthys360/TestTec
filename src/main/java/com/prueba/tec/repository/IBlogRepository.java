package com.prueba.tec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.tec.entidades.Blogs;

@Repository
public interface IBlogRepository extends JpaRepository<Blogs, Long>{
	
	@Query(value = "Select * from Blogs WHERE PK_BLOG=:id", nativeQuery = true)
	Blogs findRecordById(@Param("id") Long id);
	
	@Query(value = "Select * from Blogs ", nativeQuery = true)
	List<Blogs> findAll();

}
