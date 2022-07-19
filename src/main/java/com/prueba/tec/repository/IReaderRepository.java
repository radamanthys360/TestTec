package com.prueba.tec.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prueba.tec.entidades.Readers;

@Repository
public interface IReaderRepository extends JpaRepository<Readers, Long>{
	
	@Query(value = "Select * from READERS WHERE PK_READER=:id", nativeQuery = true)
	Readers findRecordById(@Param("id") Long id);
	
	@Query(value = "Select * from READERS ", nativeQuery = true)
	List<Readers> findAll();

}
