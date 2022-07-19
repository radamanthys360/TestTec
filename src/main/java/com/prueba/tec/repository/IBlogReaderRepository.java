package com.prueba.tec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prueba.tec.entidades.BlogsReaders;

@Repository
public interface IBlogReaderRepository extends JpaRepository<BlogsReaders, Long>{
	
	@Query(value = "Select * from blogs_readers ", nativeQuery = true)
	List<BlogsReaders> findAll();
	
	@Query(value = "Select * from blogs_readers WHERE r_id=:idReader and b_id=:idBlog", nativeQuery = true)
	BlogsReaders findRecordById(@Param("idBlog") Long idBlog,@Param("idReader") Long idReader );

}
